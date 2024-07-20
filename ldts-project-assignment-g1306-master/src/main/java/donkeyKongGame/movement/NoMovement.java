package donkeyKongGame.movement;

import donkeyKongGame.elements.Element;
import com.googlecode.lanterna.input.KeyStroke;

public class NoMovement implements MoveStrategy{

    @Override
    public void move(Element element, KeyStroke key) {}

    @Override
    public boolean canMove(Element element) {
        return false;
    }

}
