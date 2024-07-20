package donkeyKongGame;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DkApplication {

    ApplicationState state;
    Screen screen;

    private Font fontImport() throws IOException, FontFormatException {
        InputStream inpStr = new FileInputStream("src/main/resources/SquareUpdated.ttf");
        Font square = Font.createFont(Font.TRUETYPE_FONT,inpStr);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(square);
        return square.deriveFont(Font.PLAIN, 14);
    }

    private Terminal createTerminal() throws IOException, FontFormatException {
        Font loadedSquare = fontImport();
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedSquare);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setInitialTerminalSize(new TerminalSize(70,60));
        return terminalFactory.createTerminal();
    }

    private void setUpScreen(){
            try {
                Terminal terminal = createTerminal();

                ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() { // close lanterna if window close
                    @Override
                    public void windowClosing(WindowEvent e) {
                        e.getWindow().dispose();
                    }
                });
                screen = new TerminalScreen(terminal);
                screen.setCursorPosition(null); // we don't need a cursor
                screen.startScreen(); // screens must be started
                screen.doResizeIfNecessary(); // resize screen if necessary
            }catch (IOException | FontFormatException exception) {
                exception.printStackTrace();
            }
    }


    public DkApplication(){
        this.state = new MenuState(this);
        setUpScreen();
    }

    public DkApplication(ApplicationState state){
        this.state = state;
        setUpScreen();
    }

    public void changeState(ApplicationState state){
        this.state = state;
    }

    public void run() throws IOException, InterruptedException {
        while(state != null){
            this.state.run(screen);
        }
    }

    public ApplicationState getState(){
        return state;
    }
}
