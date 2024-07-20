package donkeyKongGame;

import com.googlecode.lanterna.TextColor;
import donkeyKongGame.elements.*;
import donkeyKongGame.elements.enemy.DonkeyKong;
import donkeyKongGame.elements.enemy.Enemy;
import donkeyKongGame.elements.OilBarrel;
import donkeyKongGame.movement.MoveMario;
import donkeyKongGame.movement.NoMovement;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;


public class DkArena {
    Spawner spawner;
    Mario mario;
    DonkeyKong donkeyKong;
    Pauline pauline;
    OilBarrel oilBarrel;
    ArrayList<Hammer> hammers = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Ladder> ladders = new ArrayList<>();
    int points = 0;
    long startTime =  System.currentTimeMillis();
    long time;
    int bonus = 5000;

    private final TextGraphics graphics;

    public DkArena(TextGraphics graphics) {
        this.graphics = graphics;
        createElements();
        spawner = new Spawner(enemies,ladders,graphics);
    }

    public int getPoints(){
        return points+bonus;
    }

    private void createElements(){
        this.mario  = new Mario(25,44,new MoveMario(ladders), graphics);
        this.oilBarrel = new OilBarrel(23,44,new NoMovement(),graphics);
        this.donkeyKong = new DonkeyKong(24,14,new NoMovement(), graphics);
        this.pauline = new Pauline(30, 10, new NoMovement(), graphics);
        hammers.add(new Hammer(39,38,new NoMovement(),graphics));
        hammers.add(new Hammer(31,26,new NoMovement(),graphics));
        createPlatforms();
        createLadders();
    }

    private void createPlatforms(){
        //Create Platforms
        for(int i=0; i<6; i++){
            for(int j=0; j < 26; j++){
                if(i%2 == 0 || i == 5){
                    platforms.add(new Platform(21+j,15+(6*i), new NoMovement(), graphics));
                }
                else{
                    platforms.add(new Platform(23+j,15+(6*i), new NoMovement(), graphics));
                }
            }
            platforms.add(new Platform(30+i,11,new NoMovement(),graphics));
        }
        platforms.add(new Platform(21+26,15+30, new NoMovement(), graphics));
        platforms.add(new Platform(21+27,15+30, new NoMovement(), graphics));
    }

    private void createLadders(){
        //Create Ladders
        for(int i=0;i<6;i++) {
            if (i != 2) {
                ladders.add(new Ladder(29,39+i,new NoMovement(),graphics));
                ladders.add(new Ladder(30,15+i,new NoMovement(),graphics));
            }
            if(i!=1){
                ladders.add(new Ladder(27,27+i,new NoMovement(),graphics));
                if(i!=2) {
                    ladders.add(new Ladder(40,21+i,new NoMovement(),graphics));
                }
            }
            ladders.add(new Ladder(42,39+i,new NoMovement(),graphics));
            ladders.add(new Ladder(25,33+i,new NoMovement(),graphics));
            ladders.add(new Ladder(31,33+i,new NoMovement(),graphics));
            ladders.add(new Ladder(33,27+i,new NoMovement(),graphics));
            ladders.add(new Ladder(42,27+i,new NoMovement(),graphics));
            ladders.add(new Ladder(25,21+i,new NoMovement(),graphics));
            ladders.add(new Ladder(28,21+i,new NoMovement(),graphics));
            ladders.add(new Ladder(42,15+i,new NoMovement(),graphics));

            if(i < 4) ladders.add(new Ladder(34,14-i,new NoMovement(),graphics));
            ladders.add(new Ladder(29,14-i,new NoMovement(),graphics));
            ladders.add(new Ladder(28,14-i,new NoMovement(),graphics));
        }
    }


    public void draw(){

        for(Platform p : platforms)
            p.display();
        for(Ladder l : ladders)
            l.display();
        for(Enemy enemy : enemies)
            enemy.display();
        for(Hammer hammer : hammers)
            hammer.display();
        mario.display();

        donkeyKong.display();
        pauline.display();
        oilBarrel.display();

        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(50,5,String.valueOf(points));
        graphics.putString(10,5,String.valueOf(time/1000));
        graphics.putString(10,7, "BONUS");
        graphics.putString(10,8,String.valueOf(bonus));
    }

    public void collision(){

        ListIterator<Enemy> enemiesIter = enemies.listIterator();

        if(!mario.hasHammer()){ lookForHammer();}

        while(enemiesIter.hasNext()){
            Enemy enemy = enemiesIter.next();
            if ((collidesWithMario(enemy) && mario.hasHammer()) || collidesWithOilBarrel(enemy)) enemiesIter.remove();
        }
    }

    private void lookForHammer(){
        ListIterator<Hammer> hammersIter = hammers.listIterator();

        while(hammersIter.hasNext()){
            if(mario.getPosition().equals(hammersIter.next().getPosition())){
                mario.pickHammer(hammersIter.previous());
                hammersIter.remove();
            }
        }
    }

    private boolean collidesWithMario(Enemy enemy){
        if(enemy.getPosition().equals(mario.getPosition())){
            if(mario.hasHammer()){
                points += (Objects.equals(enemy.getType(), "FireBall")) ? 200 : 100;
            }
            else mario.setDead();
            return true;
        }
        return false;
    }

    private boolean collidesWithOilBarrel(Enemy enemy){
        if(enemy.getPosition().equals(oilBarrel.getPosition()) && !Objects.equals(enemy.getType(), "FireBall")) {
            if (Objects.equals(enemy.getType(), "BlueBarrel"))
                spawner.spawnFireBall();
            return true;
        }
        return false;
    }

    public boolean isGameOver(){
        return !mario.isAlive();
    }

    public boolean won(){
        return (mario.getPosition().equals(new Position(34,10)));
    }

    public void spawner(int level){
        spawner.run(level);
    }


    public void run(KeyStroke key){
        time = System.currentTimeMillis() - startTime;
        bonus = (bonus <= 0) ? 0 : (5000 - (int)(time/1000)*100);

        if(mario.hasHammer() && mario.canDropHammer())
            mario.dropHammer();

        mario.move(key);
        collision();
        updatePoints();

        if(isGameOver()){return;}

        for(Enemy enemy : enemies){
            enemy.move(null);
        }
        collision();
    }

    private void updatePoints(){
        if(mario.getJumpState()) {
            if (checkBarrelBelow(mario.getPosition(), graphics) || checkBlueBarrelBelow(mario.getPosition(), graphics))
                points += 100;
            else if (checkFireballBelow(mario.getPosition(), graphics))
                points += 200;
        }
    }

    private boolean checkBarrelBelow(Position p, TextGraphics graphics) {
        for (int i = 1; i <= 3; i++)
            if (graphics.getCharacter(p.getX(), p.getY() + i).getCharacterString().equals("b"))
                return true;
        return false;
    }

    private boolean checkBlueBarrelBelow(Position p, TextGraphics graphics){
        for (int i = 1; i <= 3; i++)
            if (graphics.getCharacter(p.getX(), p.getY() + i).getCharacterString().equals("f")){
                return true;}
        return false;
    }

    private boolean checkFireballBelow(Position p, TextGraphics graphics){
        for (int i = 1; i <= 3; i++)
            if (graphics.getCharacter(p.getX(), p.getY() + i).getCharacterString().equals("r"))
                return true;
        return false;
    }

}
