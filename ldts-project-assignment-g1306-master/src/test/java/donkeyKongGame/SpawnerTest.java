package donkeyKongGame;

import donkeyKongGame.elements.enemy.BlueBarrel;
import donkeyKongGame.elements.enemy.Enemy;
import donkeyKongGame.elements.OilBarrel;
import donkeyKongGame.elements.Ladder;
import donkeyKongGame.elements.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class SpawnerTest {
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Ladder> ladders = new ArrayList<>();
    private static DkArena dkArena;
    private static TextGraphics graphics;

    @BeforeAll
    public static void setUp(){
        graphics = mock(TextGraphics.class);
        dkArena = new DkArena(graphics);
    }

    @Test
    public void spawnerTest() throws InterruptedException{

        dkArena.spawner = new Spawner(enemies,ladders,graphics);

        Thread.sleep(3000);
        dkArena.spawner.run(1);


        Assertions.assertEquals(enemies.size(),2);
    }

    @Test
    public void spawnerFireBallTest(){
        BlueBarrel blueBarrel = mock(BlueBarrel.class);
        OilBarrel oilBarrel = mock(OilBarrel.class);
        Mockito.when(blueBarrel.getPosition()).thenReturn(new Position(1,1));
        Mockito.when(blueBarrel.getType()).thenReturn("BlueBarrel");
        Mockito.when(oilBarrel.getPosition()).thenReturn(new Position(1,1));
        enemies.add(blueBarrel);
        dkArena.enemies = this.enemies;
        dkArena.oilBarrel = oilBarrel;
        dkArena.spawner = new Spawner(enemies,ladders,graphics);
        dkArena.collision();
        dkArena.spawner.run(1);

        Assertions.assertEquals(enemies.get(1).getType(),"FireBall");

    }
}