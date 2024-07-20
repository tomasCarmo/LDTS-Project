package donkeyKongGame.elements;

import com.googlecode.lanterna.TextColor;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hammer extends Element{

    private long startTime = 0;

    public Hammer(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y,moveStrategy, graphics);
    }

    @Override
    public void display() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#f69e51"));
        graphics.putString(getPosition().getX(),getPosition().getY(), "h");
    }

    public void startTime(){
        startTime = System.currentTimeMillis();
    }

    public boolean didTimeEnded(){
        return System.currentTimeMillis()-startTime >= 5000;
    }
}