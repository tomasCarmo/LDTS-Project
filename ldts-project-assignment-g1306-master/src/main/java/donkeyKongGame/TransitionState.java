package donkeyKongGame;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class TransitionState implements ApplicationState{

    DkApplication dkApplication;
    private final int level;
    private int points;

    public TransitionState(DkApplication dkApplication, int level,int points) {
        this.dkApplication = dkApplication;
        this.level = level+1;
        this.points = points;
    }

    @Override
    public void draw(TextGraphics graphics){

        graphics.putString(20,50, "HOW HIGH CAN YOU GET?");
        graphics.putString(33,45, "d");
        graphics.putString(23,45, "25 M");
        if (level >= 2){
            graphics.putString(33,42, "d");
            graphics.putString(23,42, "50 M");
        }if (level == 3){
            graphics.putString(33,39, "d");
            graphics.putString(23,39, "75 M");
        }
    }

    @Override
    public void run(Screen screen) throws IOException {

        TextGraphics graphics = screen.newTextGraphics();
        screen.clear();
        points += 15000*level-1;  //Just bonus points when the user completes a new level

        long startTime = System.currentTimeMillis();
        long time;
        do{
            time = System.currentTimeMillis()- startTime;
            draw(graphics);
            screen.refresh();
        }while (time < 3000);
        dkApplication.changeState(new PlayState(dkApplication,new DkArena(graphics),level,points));
    }

    @Override
    public KeyStroke readInput(Screen screen) throws IOException {
        return null;
    }
}
