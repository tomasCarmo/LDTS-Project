package donkeyKongGame.elements.enemy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnemyTypeTest {
    @Test
    public void getBarrelTypeTest(){
        Barrel barrel = new Barrel(0,0,null,null);

        Assertions.assertEquals("NormalBarrel", barrel.getType());
    }

    @Test
    public void getBlueBarrelTypeTest(){
        BlueBarrel blueBarrel = new BlueBarrel(0,0,null,null);

        Assertions.assertEquals("BlueBarrel", blueBarrel.getType());
    }

    @Test
    public void getDonkeyKongTypeTest(){
        DonkeyKong donkeyKong = new DonkeyKong(0,0,null,null);

        Assertions.assertEquals("DonkeyKong", donkeyKong.getType());
    }

    @Test
    public void getFireballTypeTest(){
        FireBall fireball = new FireBall(0,0,null,null);

        Assertions.assertEquals("FireBall", fireball.getType());
    }
}
