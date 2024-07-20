package donkeyKongGame;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MenuState implements ApplicationState{

    DkApplication dkApplication;

    public MenuState(DkApplication dkApplication){
        this.dkApplication = dkApplication;
    }

    @Override
    public void draw(TextGraphics graphics) {

        Leaderboard leaderboard = new Leaderboard("src/main/java/DonkeyKongGame/leaderboard.txt");
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff0f5a"));
        graphics.putString(28,16, "DONKEY KONG", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#f6bc57"));
        graphics.putString(27,16, "b", SGR.BOLD);
        graphics.putString(39,16, "b", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(28,20, "PLAY");
        graphics.putString(33,20, "(ENTER)");
        graphics.putString(28,22, "EXIT");
        graphics.putString(33,22, "(ESC)");

        leaderboard.displayLeaderboard(graphics);
    }

    @Override
    public void run(Screen screen) throws IOException {
        screen.clear();
        draw(screen.newTextGraphics());
        screen.refresh();

        KeyStroke key = readInput(screen);
        if(key.getKeyType() == KeyType.Enter){
            this.dkApplication.changeState(new PlayState(dkApplication, new DkArena(screen.newTextGraphics()),1,0));

        }else if (key.getKeyType() == KeyType.Escape || key.getKeyType() == KeyType.EOF){
            this.dkApplication.changeState(null);
            screen.close();
        }
    }

    @Override
    public KeyStroke readInput(Screen screen) throws IOException {
        KeyStroke key = screen.readInput();

        while(key.getKeyType() != KeyType.Enter && key.getKeyType() != KeyType.EOF && key.getKeyType() != KeyType.Escape) {
            key = screen.readInput();
        }
        return key;
    }
}
