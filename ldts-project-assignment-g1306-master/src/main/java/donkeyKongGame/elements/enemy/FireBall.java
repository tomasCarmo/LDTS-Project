
package donkeyKongGame.elements.enemy;


import com.googlecode.lanterna.TextColor;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class FireBall extends Enemy {

    public FireBall(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y,moveStrategy, graphics);
    }

    @Override
    public void display() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#fde3a0"));
        graphics.putString(getPosition().getX(),getPosition().getY(), "r");
    }

    @Override
    public String getType() {
        return "FireBall";
    }
}