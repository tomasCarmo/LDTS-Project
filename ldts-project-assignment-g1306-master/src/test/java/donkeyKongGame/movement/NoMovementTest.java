package donkeyKongGame.movement;

import donkeyKongGame.elements.Hammer;
import donkeyKongGame.elements.Position;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class NoMovementTest {
    @Test
    public void hammerNoMovementTest(){
        Hammer hammer = new Hammer(0,0,new NoMovement(),null);

        hammer.move(null);

        Assertions.assertEquals(hammer.getPosition(), new Position(0,0));
    }

    @Test
    public void hammerCanMoveTest(){
        Hammer hammer = new Hammer(0,0,new NoMovement(), null);

        Assertions.assertFalse(hammer.getMoveStrategy().canMove(hammer));
    }
}
