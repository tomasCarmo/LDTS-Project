package donkeyKongGame;
import donkeyKongGame.elements.enemy.Barrel;
import donkeyKongGame.elements.enemy.BlueBarrel;
import donkeyKongGame.elements.enemy.Enemy;
import donkeyKongGame.elements.enemy.FireBall;
import donkeyKongGame.elements.Ladder;
import donkeyKongGame.movement.MoveFireBall;
import donkeyKongGame.movement.MoveNormalBarrel;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.ArrayList;
import java.util.Random;


public class Spawner {
    private long startTime;
    private final TextGraphics graphics;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Ladder> ladders;
    private int fireBallsToSpawn = 0;

    public Spawner(ArrayList<Enemy> enemies, ArrayList<Ladder> ladders, TextGraphics graphics) {
        this.enemies = enemies;
        this.graphics = graphics;
        this.ladders = ladders;
        startTime = System.currentTimeMillis();

        enemies.add(new BlueBarrel(21,14,new MoveNormalBarrel(),graphics));
    }

    public void spawnFireBall(){
        fireBallsToSpawn++;
    }

    public void run(int level){
        Random rand = new Random();
        int number = rand.nextInt(3);

        if(System.currentTimeMillis()-startTime >= 3000- 600L *level){
            if(number == 0) enemies.add(new BlueBarrel(21,14,new MoveNormalBarrel(),graphics));
            else enemies.add(new Barrel(21,14,new MoveNormalBarrel(),graphics));

            startTime = System.currentTimeMillis();
        }
        for(int i=0; i<fireBallsToSpawn; i++){
            enemies.add(new FireBall(24,44,new MoveFireBall(ladders),graphics));
        }
        fireBallsToSpawn = 0;
    }
}