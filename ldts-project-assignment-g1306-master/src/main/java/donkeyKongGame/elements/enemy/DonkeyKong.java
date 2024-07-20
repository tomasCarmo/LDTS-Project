package donkeyKongGame.elements.enemy;
import com.googlecode.lanterna.TextColor;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class DonkeyKong extends Enemy {

    public DonkeyKong(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y, moveStrategy, graphics);
    }

    @Override
    public void display() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#f1d1b3"));
        graphics.putString(getPosition().getX(),getPosition().getY(), "d");
    }

    @Override
    public String getType() {
        return "DonkeyKong";
    }
}