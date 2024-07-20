package donkeyKongGame.elements;

import com.googlecode.lanterna.TextColor;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class OilBarrel extends Element {

    public OilBarrel(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y, moveStrategy, graphics);
    }

    @Override
    public void display() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#2b3cfa"));
        graphics.putString(getPosition().getX(),getPosition().getY(), "o");
    }
}
