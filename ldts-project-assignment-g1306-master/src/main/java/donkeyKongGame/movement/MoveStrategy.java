package donkeyKongGame.movement;

import donkeyKongGame.elements.Element;
import com.googlecode.lanterna.input.KeyStroke;

public interface MoveStrategy {
    void move(Element element, KeyStroke key);
    boolean canMove(Element element);
}