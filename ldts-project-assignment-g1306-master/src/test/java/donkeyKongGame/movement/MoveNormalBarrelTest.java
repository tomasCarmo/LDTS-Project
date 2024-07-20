package donkeyKongGame.movement;

import donkeyKongGame.elements.enemy.Barrel;
import donkeyKongGame.elements.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

public class MoveNormalBarrelTest {
    private static TextGraphics graphics;
    private static TextCharacter character;

    @BeforeAll
    public static void setup(){

        graphics = mock(TextGraphics.class);
        character = mock(TextCharacter.class);

    }

    @Test
    public void barrelMoveRight() throws InterruptedException{
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);

        Mockito.when(character.getCharacterString()).thenReturn("p");
        Barrel barrel = new Barrel(11,8,new MoveNormalBarrel(),graphics);

        barrel.getDirection().setDirectionID(0);

        Thread.sleep(72);
        barrel.move(null);

        Assertions.assertEquals(barrel.getPosition(), new Position(12,8));

    }

    @Test
    public void barrelMoveLeft() throws InterruptedException{
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);

        Mockito.when(character.getCharacterString()).thenReturn("p");
        Barrel barrel = new Barrel(11,8,new MoveNormalBarrel(),graphics);
        barrel.getDirection().setDirectionID(2);

        Thread.sleep(72);
        barrel.move(null);
        barrel.move(null);

        Assertions.assertEquals(barrel.getPosition(), new Position(10,8));
    }

    @Test
    public void barrelMoveDownRight() throws InterruptedException{
        MoveNormalBarrel moveNormalBarrel = new MoveNormalBarrel();
        Random random = mock(Random.class);
        moveNormalBarrel.setRand(random);
        Barrel barrel = new Barrel(10,14,moveNormalBarrel,graphics);
        barrel.getDirection().setDirectionID(0);
        Mockito.when(random.nextInt()).thenReturn(0);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);

        Mockito.when(character.getCharacterString()).thenReturn("p"," " ," "," "," "," "," ","p");

        for(int i = 0; i < 8; i++){
            Thread.sleep(72);
            barrel.move(null);
        }


        Assertions.assertEquals(barrel.getPosition(), new Position(10,20));
    }

    @Test
    public void barrelMoveDownLeft() throws InterruptedException{
        MoveNormalBarrel moveNormalBarrel = new MoveNormalBarrel();
        Random random = mock(Random.class);
        moveNormalBarrel.setRand(random);
        Barrel barrel = new Barrel(10,20,moveNormalBarrel,graphics);
        barrel.getDirection().setDirectionID(2);
        Mockito.when(random.nextInt()).thenReturn(0);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);

        Mockito.when(character.getCharacterString()).thenReturn("p"," " ," "," "," "," "," ","p");


        for(int i = 0; i < 8; i++){
            Thread.sleep(72);
            barrel.move(null);
        }

        Assertions.assertEquals(barrel.getPosition(), new Position(10,26));
    }

    @Test
    public void barrelNoMovement(){
        Barrel barrel = new Barrel(11,9,new MoveNormalBarrel(),graphics);
        barrel.getDirection().setDirectionID(4);
        barrel.move(null);

        Assertions.assertEquals(barrel.getPosition(), new Position(11,9));
    }

    @Test
    public void canMoveNoDirection(){
        MoveNormalBarrel moveStrategy = new MoveNormalBarrel();
        Barrel barrel = new Barrel(0,0,moveStrategy,graphics);
        barrel.getDirection().setDirectionID(4);

        Assertions.assertTrue(moveStrategy.canMove(barrel));
    }

    @Test
    public void canMoveEnd(){
        MoveNormalBarrel moveStrategy = new MoveNormalBarrel();
        Barrel barrel = new Barrel(21,44,moveStrategy,graphics);


        Assertions.assertTrue(moveStrategy.canMove(barrel));

    }

    @Test
    public void changeOptionTest() throws InterruptedException{
        MoveNormalBarrel moveNormalBarrel = new MoveNormalBarrel();
        Random random = mock(Random.class);
        moveNormalBarrel.setRand(random);
        Mockito.when(random.nextInt(2)).thenReturn(1);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("l");
        Barrel barrel = new Barrel(11,8,moveNormalBarrel,graphics);
        barrel.getDirection().setDirectionID(0);

        Thread.sleep(72);
        barrel.move(null);
        barrel.move(null);

        Assertions.assertEquals(barrel.getPosition(), new Position(11,9));
    }


}