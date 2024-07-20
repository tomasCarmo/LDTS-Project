package donkeyKongGame.elements.enemy;
import com.googlecode.lanterna.TextColor;
import donkeyKongGame.elements.Direction;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Barrel extends Enemy {

    public Barrel(int x, int y,MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x,y,moveStrategy, graphics);
        direction = new Direction();
    }

    @Override
    public void display() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#f6bc57"));
        if (direction.getDirectionName() == Direction.DirectionEnum.DOWN)
            graphics.putString(getPosition().getX(), getPosition().getY(), "g");
        else
            graphics.putString(getPosition().getX(), getPosition().getY(), "b");
    }

    @Override
    public String getType() {
        return "NormalBarrel";
    }
}
