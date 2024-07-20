package donkeyKongGame.elements;

import com.googlecode.lanterna.TextColor;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Platform extends Element {

    public Platform(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y, moveStrategy, graphics);
    }

    @Override
    public void display(){
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff0f5a"));
        graphics.putString(getPosition().getX(),getPosition().getY(), "p");
    }
}
