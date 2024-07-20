package donkeyKongGame.elements;

import com.googlecode.lanterna.TextColor;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ladder extends Element {

    public Ladder(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y, moveStrategy, graphics);
    }

    @Override
    public void display(){
        graphics.setForegroundColor(TextColor.Factory.fromString("#2aeee7"));
        graphics.putString(getPosition().getX(),getPosition().getY(), "l");
    }
}
