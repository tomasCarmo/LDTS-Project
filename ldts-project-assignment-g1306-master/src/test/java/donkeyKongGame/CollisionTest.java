package donkeyKongGame;

import donkeyKongGame.elements.Mario;
import donkeyKongGame.elements.Ladder;
import donkeyKongGame.elements.Hammer;
import donkeyKongGame.elements.Position;
import donkeyKongGame.elements.enemy.Barrel;
import donkeyKongGame.elements.enemy.Enemy;
import donkeyKongGame.movement.MoveMario;
import donkeyKongGame.movement.MoveNormalBarrel;
import donkeyKongGame.movement.NoMovement;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CollisionTest {
    private static TextGraphics graphics;
    private static TextCharacter character;
    private static final ArrayList<Ladder> ladders = new ArrayList<>();
    private static final ArrayList<Enemy> enemies = new ArrayList<>();
    private static final ArrayList<Hammer> hammers = new ArrayList<>();

    @BeforeAll
    public static void setup(){

        graphics = mock(TextGraphics.class);
        character = mock(TextCharacter.class);

    }

    @Test
    public void MarioToBarrelCollision(){
        DkArena dkArena = new DkArena(graphics);
        Barrel barrel = mock(Barrel.class);
        Mario mario = new Mario(12,8,new MoveMario(ladders),graphics);
        enemies.add(barrel);

        Mockito.when(barrel.getPosition()).thenReturn(new Position(11,8));
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("p");
        dkArena.enemies = enemies;
        dkArena.mario = mario;
        mario.getDirection().setDirectionID(2);
        dkArena.run(new KeyStroke(KeyType.ArrowLeft));

        Assertions.assertFalse(dkArena.mario.isAlive());
    }

    @Test
    public void barrelToMarioCollision() throws InterruptedException{
        DkArena dkArena = new DkArena(graphics);
        dkArena.ladders = ladders;


        Mario mario = new Mario(12,8,new MoveMario(ladders),graphics);

        MoveNormalBarrel moveNormalBarrel = new MoveNormalBarrel();
        Random random = mock(Random.class);
        moveNormalBarrel.setRand(random);
        Barrel barrel = new Barrel(11,8,moveNormalBarrel,graphics);
        Mockito.when(random.nextInt()).thenReturn(0);
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("p");
        enemies.add(barrel);
        dkArena.enemies = enemies;
        dkArena.mario = mario;



        Thread.sleep(72);
        dkArena.run(null);

        Assertions.assertFalse(mario.isAlive());
    }


    @Test
    public void MarioToBarrelCollisionWithHammer(){
        DkArena dkArena = new DkArena(graphics);
        Barrel barrel = mock(Barrel.class);
        Mario mario = new Mario(13,8,new MoveMario(ladders),graphics);
        Hammer hammer = new Hammer(12,8,new NoMovement(),graphics);
        enemies.add(barrel);
        hammers.add(hammer);

        Mockito.when(barrel.getPosition()).thenReturn(new Position(11,8));
        Mockito.when(barrel.getType()).thenReturn("Barrel");
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("p");
        dkArena.hammers = hammers;
        dkArena.enemies = enemies;
        dkArena.mario = mario;
        mario.getDirection().setDirectionID(2);
        dkArena.run(new KeyStroke(KeyType.ArrowLeft));
        dkArena.run(new KeyStroke(KeyType.ArrowLeft));

        Assertions.assertEquals(enemies.size(), 0);
    }

    @Test
    public void collidingAfterDroppingHammer(){
        DkArena dkArena = new DkArena(graphics);
        Barrel barrel = mock(Barrel.class);
        Hammer hammer = mock(Hammer.class);
        Mario mario = new Mario(12,8,new MoveMario(ladders),graphics);
        enemies.add(barrel);
        hammers.add(hammer);
        when(hammer.didTimeEnded()).thenReturn(true);
        Mockito.when(barrel.getPosition()).thenReturn(new Position(11,8));
        Mockito.when(graphics.getCharacter(anyInt(),anyInt())).thenReturn(character);
        Mockito.when(character.getCharacterString()).thenReturn("p");
        dkArena.hammers = hammers;
        dkArena.enemies = enemies;

        dkArena.mario = mario;

        mario.getDirection().setDirectionID(2);
        mario.pickHammer(hammer);

        Assertions.assertTrue(mario.hasHammer());
        dkArena.run(new KeyStroke(KeyType.ArrowLeft));

        Assertions.assertFalse(mario.isAlive());
    }



}
