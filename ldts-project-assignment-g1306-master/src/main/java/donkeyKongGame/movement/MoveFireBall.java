package donkeyKongGame.movement;
import donkeyKongGame.elements.Direction;
import donkeyKongGame.elements.Element;
import donkeyKongGame.elements.Ladder;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.Random;


public class MoveFireBall implements MoveStrategy{
    private final ArrayList<Ladder> ladders;
    private long startTime;

    public MoveFireBall(ArrayList<Ladder> ladders) {
        this.ladders = ladders;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void move(Element element, KeyStroke key) {
        if(System.currentTimeMillis()-startTime < 500){
            return;
        }
        startTime = System.currentTimeMillis();

        if(!canMove(element)){
            newPosition(element);
        } //In case he can't move it chooses the new position
        changeOption(element);

        switch (element.getDirection().getDirectionName()){
            case RIGHT:
                element.moveRight();
                break;
            case LEFT:
                element.moveLeft();
                break;
            case DOWN:
                element.moveDown();
                break;
            case UP:
                element.moveUp();
            case NODIRECTION:
                element.noMove();
                break;
        }
    }

    @Override
    public boolean canMove(Element element) {
        TextGraphics graphics = element.getGraphics();

        if(element.getDirection().getDirectionName() == Direction.DirectionEnum.RIGHT &&
                !graphics.getCharacter(element.getPosition().getX() + 1, element.getPosition().getY() + 1).getCharacterString().equals(" "))
            return true;

        else if(element.getDirection().getDirectionName() == Direction.DirectionEnum.LEFT &&
                !graphics.getCharacter(element.getPosition().getX() - 1, element.getPosition().getY() + 1).getCharacterString().equals(" "))
            return true;

        else if(element.getDirection().getDirectionName() == Direction.DirectionEnum.DOWN &&
                !graphics.getCharacter(element.getPosition().getX(), element.getPosition().getY() + 1).getCharacterString().equals("p"))
            return true;

        else if(element.getDirection().getDirectionName() == Direction.DirectionEnum.UP){
            for(Ladder l : ladders){
                if(l.getPosition().equals(element.getPosition()) || (45-element.getPosition().getY()-1)%6 != 0)
                    return true;
            }
        }
        return false;
    }

    private void changeOption(Element element){
        int elementDir = element.getDirection().getDirectionID();

        switch (element.getDirection().getDirectionName()){
            case RIGHT,LEFT ->{
                element.getDirection().setDirectionID(3);
                if(canMove(element)){
                    Random rand = new Random();
                    if(rand.nextInt(2) == 1)
                        return;
                }
                element.getDirection().setDirectionID(1);
                if(canMove(element)){
                    Random rand = new Random();
                    if(rand.nextInt(2) == 1)
                        return;
                }
            }
        }
        element.getDirection().setDirectionID(elementDir);
    }

    private void newPosition(Element element){
        switch(element.getDirection().getDirectionName()){
            case RIGHT -> element.getDirection().setDirectionID(2);
            case LEFT -> element.getDirection().setDirectionID(0);
            case UP -> {
                Random rand = new Random();
                element.getDirection().setDirectionID(rand.nextInt(3)); //Chooses his direction when reaches the top of the stairs
            }
            case DOWN -> {
                Random rand = new Random();
                int number = rand.nextInt(3);
                if(number == 1)
                    number = 3;
                element.getDirection().setDirectionID(number); //Chooses his direction when reaches the platform
            }
        }
    }
}
