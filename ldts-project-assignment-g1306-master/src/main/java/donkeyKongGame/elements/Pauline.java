package donkeyKongGame.elements;

import com.googlecode.lanterna.TextColor;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pauline extends Element{

    public Pauline(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y,moveStrategy, graphics);
    }

    @Override
    public void display() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffb6c1"));
        graphics.putString(getPosition().getX(),getPosition().getY(), "q");
    }
}