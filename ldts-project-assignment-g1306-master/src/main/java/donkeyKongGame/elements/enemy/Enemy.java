package donkeyKongGame.elements.enemy;

import donkeyKongGame.elements.Element;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Enemy extends Element {

    public Enemy(int x, int y, MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x, y,moveStrategy, graphics);
    }

    public abstract String getType();

}