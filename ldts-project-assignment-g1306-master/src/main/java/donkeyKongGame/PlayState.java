package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class PlayState implements ApplicationState{

    DkApplication dkApplication;
    DkArena dkArena;
    private final int points;
    private final int level;


    public PlayState(DkApplication dkApplication, DkArena arena,int level, int points){
        this.dkApplication = dkApplication;
        this.dkArena = arena;
        this.points = points;
        this.level = level;
    }

    @Override
    public void draw(TextGraphics graphics) {
        dkArena.draw();
    }

    @Override
    public KeyStroke readInput(Screen screen) throws IOException {
        return screen.pollInput();
    }

    @Override
    public void run(Screen screen) throws IOException, InterruptedException {
        int FPS = 15;
        int frameTime = 1000 / FPS;
        KeyStroke key = readInput(screen);
        while (key == null || key.getKeyType() != KeyType.EOF ) {
            long startTime = System.currentTimeMillis();

            screen.clear();
            draw(screen.newTextGraphics());
            dkArena.run(key);
            screen.refresh();

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep(sleepTime);

            if (!continueGame()){
                screen.clear();
                draw(screen.newTextGraphics());
                screen.refresh();
                Thread.sleep(1500); //Just to have time to transit
                return;
            }
            dkArena.spawner(level);
            key = readInput(screen);
        }
        screen.close();
        dkApplication.changeState(null);
    }

    private boolean continueGame(){
        if((dkArena.won() && level ==3) || dkArena.isGameOver()){
            dkApplication.changeState(new EndGameState(dkApplication, dkArena.getPoints()+points, dkArena.won()));
            return false;
        }else if (dkArena.won()){
            dkApplication.changeState(new TransitionState(dkApplication,level, dkArena.getPoints()+points));
            return false;
        }
        return true;
    }


}
