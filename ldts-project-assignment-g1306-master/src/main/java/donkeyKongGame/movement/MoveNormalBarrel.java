package donkeyKongGame.movement;

import donkeyKongGame.elements.Direction;
import donkeyKongGame.elements.Element;
import donkeyKongGame.elements.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.Objects;
import java.util.Random;

public class MoveNormalBarrel implements MoveStrategy{
    private Random rand = new Random();
    private long startTime;

    public MoveNormalBarrel() {
        startTime = System.currentTimeMillis();
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    @Override
    public void move(Element element, KeyStroke key) {
        if(System.currentTimeMillis()-startTime < 72){return;}
        startTime = System.currentTimeMillis();

        if (!canMove(element)){newPosition(element);}

        changeOption(element);

        switch (element.getDirection().getDirectionName()) {
            case RIGHT -> element.moveRight();
            case LEFT -> element.moveLeft();
            case DOWN -> element.moveDown();
            case NODIRECTION -> element.noMove();
        }
    }

    @Override
    public boolean canMove(Element element) {
        TextGraphics graphics = element.getGraphics();

        if (element.getDirection().getDirectionName() == Direction.DirectionEnum.NODIRECTION) return true;
        else if(element.getPosition().equals(new Position(21,44))){
            element.getDirection().setDirectionID(4);
            return true;
        }
        else if(element.getDirection().getDirectionName() == Direction.DirectionEnum.RIGHT &&
                !Objects.equals(graphics.getCharacter(element.getPosition().getX(), element.getPosition().getY() + 1).getCharacterString(), " "))
                return true;
        else if(element.getDirection().getDirectionName() == Direction.DirectionEnum.LEFT)
            return !Objects.equals(graphics.getCharacter(element.getPosition().getX(), element.getPosition().getY() + 1).getCharacterString(), " ");

        else return element.getDirection().getDirectionName() == Direction.DirectionEnum.DOWN && !Objects.equals(graphics.getCharacter(element.getPosition().getX(), element.getPosition().getY() + 1).getCharacterString(), "p");

    }

    private void changeOption(Element element){

        if(rand.nextInt(2) == 0) return;
        int elementDir = element.getDirection().getDirectionID();

        switch (element.getDirection().getDirectionName()){
            case RIGHT,LEFT ->{
                element.getDirection().setDirectionID(1);
                if(canMove(element)) return;
            }
        }
        element.getDirection().setDirectionID(elementDir);
    }

    private void newPosition(Element element){
        switch (element.getDirection().getDirectionName()) {
            case RIGHT, LEFT -> element.getDirection().setDirectionID(1);
            case DOWN -> {
                int platform = (45 - element.getPosition().getY() - 1) / 6;
                if (platform % 2 == 0) {
                    element.getDirection().setDirectionID(2);
                } else {
                    element.getDirection().setDirectionID(0);
                }
            }
        }
    }
}