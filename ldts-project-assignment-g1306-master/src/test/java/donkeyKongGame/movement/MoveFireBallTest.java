package donkeyKongGame.movement;

import donkeyKongGame.elements.enemy.FireBall;
import donkeyKongGame.elements.Ladder;
import donkeyKongGame.elements.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;


public class MoveFireBallTest {
    ArrayList<Ladder> ladders = new ArrayList<>();
    private static TextGraphics graphics;
    private static TextCharacter character;



    @BeforeAll
    public static void setUp() {
        graphics = mock(TextGraphics.class);
        character = mock(TextCharacter.class);

    }


    @Test
    public void FireBallMoveRight() throws InterruptedException{
        FireBall fireball = new FireBall(1,1,new MoveFireBall(ladders),graphics);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("p");
        Thread.sleep(500);
        fireball.move(null);
        Assertions.assertEquals(fireball.getPosition(), new Position(2,1));
    }

    @Test
    public void FireBallMoveLeft() throws InterruptedException{
        FireBall fireball = new FireBall(1,1,new MoveFireBall(ladders),graphics);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("p");
        Thread.sleep(500);
        fireball.getDirection().setDirectionID(2);
        fireball.move(null);
        Assertions.assertEquals(fireball.getPosition(), new Position(0,1));
    }

    @Test
    public void FireBallMoveUp() throws InterruptedException{
        FireBall fireball = new FireBall(1,1,new MoveFireBall(ladders),graphics);
        Ladder ladder = mock(Ladder.class);
        ladders.add(ladder);
        Mockito.when(ladder.getPosition()).thenReturn(new Position(1,0));
        Thread.sleep(500);
        fireball.getDirection().setDirectionID(3);
        fireball.move(null);
        Assertions.assertEquals(fireball.getPosition(), new Position(1,0));
    }

    @Test
    public void FireBallMoveDown() throws InterruptedException{
        FireBall fireball = new FireBall(1,1,new MoveFireBall(ladders),graphics);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn(" ");
        Thread.sleep(500);
        fireball.getDirection().setDirectionID(1);
        fireball.move(null);
        Assertions.assertEquals(fireball.getPosition(), new Position(1,2));
    }

    @Test
    public void FireBallChoosingPathDown() throws InterruptedException{
        FireBall fireball = new FireBall(1,1,new MoveFireBall(ladders),graphics);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("p");
        Thread.sleep(500);
        fireball.getDirection().setDirectionID(1);
        fireball.move(null);
        Assertions.assertNotEquals(fireball.getPosition(), new Position(1,2));
        Assertions.assertNotEquals(fireball.getPosition(), new Position(1,1));
    }

    @Test
    public void FireBallChoosingPathUp() throws InterruptedException{
        FireBall fireball = new FireBall(1,44,new MoveFireBall(ladders),graphics);
        Ladder ladder = mock(Ladder.class);
        ladders.add(ladder);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("p");
        Mockito.when(ladder.getPosition()).thenReturn(new Position(1,45));
        Thread.sleep(500);
        fireball.getDirection().setDirectionID(3);
        fireball.move(null);
        Assertions.assertNotEquals(fireball.getPosition(), new Position(1,43));
        Assertions.assertNotEquals(fireball.getPosition(), new Position(1,44));
    }

    @Test
    public void FireBallDontMoveRight() throws InterruptedException{
        FireBall fireball = new FireBall(1,1,new MoveFireBall(ladders),graphics);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn( " ","p");
        Thread.sleep(500);
        fireball.getDirection().setDirectionID(0);
        fireball.move(null);
        Assertions.assertEquals(fireball.getPosition(), new Position(0,1));
    }

    @Test
    public void FireBallDontMoveLeft() throws InterruptedException{
        FireBall fireball = new FireBall(1,1,new MoveFireBall(ladders),graphics);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn(" ","p");
        Thread.sleep(500);
        fireball.getDirection().setDirectionID(2);
        fireball.move(null);
        Assertions.assertEquals(fireball.getPosition(), new Position(2,1));
    }



}
