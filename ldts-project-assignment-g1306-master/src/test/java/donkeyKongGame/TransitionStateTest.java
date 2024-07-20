package donkeyKongGame;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;

public class TransitionStateTest {
    private static TextGraphics graphics;


    @BeforeAll
    public static void setUp() throws IOException {
        TerminalSize terminalSize = new TerminalSize(70, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        graphics = screen.newTextGraphics();
    }

    @Test
    public void drawTest() throws IOException {
        setUp();
        TransitionState transitionState = new TransitionState(new DkApplication(), 1,0);
        transitionState.draw(graphics);

        Assertions.assertEquals(graphics.getCharacter(20, 50).getCharacterString(), "H");
        Assertions.assertEquals(graphics.getCharacter(33, 45).getCharacterString(), "d");
        Assertions.assertEquals(graphics.getCharacter(23, 45).getCharacterString(), "2");

        transitionState = new TransitionState(new DkApplication(), 2,0);
        transitionState.draw(graphics);
        Assertions.assertEquals(graphics.getCharacter(20, 50).getCharacterString(), "H");
        Assertions.assertEquals(graphics.getCharacter(33, 45).getCharacterString(), "d");
        Assertions.assertEquals(graphics.getCharacter(33, 42).getCharacterString(), "d");
        Assertions.assertEquals(graphics.getCharacter(23, 42).getCharacterString(), "5");

        transitionState = new TransitionState(new DkApplication(), 3,0);
        transitionState.draw(graphics);
        Assertions.assertEquals(graphics.getCharacter(20, 50).getCharacterString(), "H");
        Assertions.assertEquals(graphics.getCharacter(33, 45).getCharacterString(), "d");
        Assertions.assertEquals(graphics.getCharacter(33, 42).getCharacterString(), "d");
        Assertions.assertEquals(graphics.getCharacter(33, 39).getCharacterString(), "d");
        Assertions.assertEquals(graphics.getCharacter(23, 39).getCharacterString(), "7");
    }

    @Test
    public void readInputTest() throws IOException {

        Screen screen = Mockito.mock(Screen.class);
        TransitionState transitionState = new TransitionState(new DkApplication(), 1,0);
        Assertions.assertNull(transitionState.readInput(screen));
    }

    @Test
    public void runTest() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        DkApplication dkApplication = Mockito.mock(DkApplication.class);
        TransitionState transitionState = new TransitionState(dkApplication, 1,0);
        transitionState.run(screen);
        Mockito.verify(dkApplication,Mockito.times(1)).changeState(any(ApplicationState.class));


    }
}
