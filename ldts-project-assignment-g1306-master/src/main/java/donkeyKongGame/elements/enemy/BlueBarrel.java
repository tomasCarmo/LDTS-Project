package donkeyKongGame.elements.enemy;

import com.googlecode.lanterna.TextColor;
import donkeyKongGame.elements.Direction;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BlueBarrel extends Enemy{

    public BlueBarrel(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y, moveStrategy, graphics);
        direction = new Direction();
    }

    @Override
    public void display() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0c23f5"));
        if (direction.getDirectionName() == Direction.DirectionEnum.DOWN)
            graphics.putString(getPosition().getX(), getPosition().getY(), "g");
        else
            graphics.putString(getPosition().getX(), getPosition().getY(), "f");
    }

    @Override
    public String getType() {
        return "BlueBarrel";
    }
}