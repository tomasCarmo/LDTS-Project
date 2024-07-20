package donkeyKongGame;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

import static java.lang.Character.toUpperCase;

public class EndGameState implements ApplicationState{

    private final DkApplication dkApplication;
    private Leaderboard leaderboard;
    private final int points;
    private final boolean won;

    public EndGameState(DkApplication dkApplication, int points, boolean won) {
        this.dkApplication = dkApplication;
        this.points = points;
        this.leaderboard = new Leaderboard("src/main/java/DonkeyKongGame/leaderboard.txt");
        this.won = won;
    }

    @Override
    public void draw(TextGraphics graphics) {
        if(won){
            graphics.putString(30, 30, "YOU WON",SGR.BLINK);
        }else{
            graphics.putString(30, 30, "GAME OVER");
        }
        if (leaderboard.goesToLeaderboard(points)){
            graphics.putString(20, 35, "NAME:");
        }else {
            graphics.putString(22,35, "[PRESS ENTER TO CONTINUE]");
        }
    }

    @Override
    public void run(Screen screen) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        screen.clear();
        draw(graphics);
        screen.refresh();

        if (leaderboard.goesToLeaderboard(points))
            leaderboard.updateFile(readName(screen),points);

        KeyStroke key;
        do {
            key = readInput(screen);
        }while (key.getKeyType() != KeyType.Enter);

        dkApplication.changeState(new MenuState(dkApplication));
    }

    @Override
    public KeyStroke readInput(Screen screen) throws IOException {

        KeyStroke key= screen.readInput();
        while (true){
            if(key.getKeyType() == KeyType.Character){
                if (key.getCharacter().toString().matches("[a-zA-Z]+") || key.getCharacter().toString().matches("[0-9]")){
                    return key;
                }
            }else if(key.getKeyType() == KeyType.Enter)
                return key;
            key = screen.readInput();
        }
    }

    public String readName(Screen screen) throws IOException {
        KeyStroke key = readInput(screen);
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            if (key.getKeyType() == KeyType.Enter) {
                name.append(" ".repeat(Math.max(0, 5 - name.length())));
                break;
            }
            name.append(toUpperCase(key.getCharacter()));
            screen.newTextGraphics().setCharacter(27 + i, 35, toUpperCase(key.getCharacter()));
            screen.refresh();
            key = readInput(screen);
        }
        return name.toString();
    }

    public void setLeaderboard(Leaderboard leaderboard){
        this.leaderboard = leaderboard;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }
}
