package donkeyKongGame.movement;

import donkeyKongGame.elements.Direction;
import donkeyKongGame.elements.Ladder;
import com.googlecode.lanterna.graphics.TextGraphics;
import donkeyKongGame.elements.Element;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.Objects;


public class MoveMario implements MoveStrategy{
    ArrayList<Ladder> ladders;

    public MoveMario(ArrayList<Ladder> ladders) {
        this.ladders = ladders;
    }

    @Override
    public void move(Element element, KeyStroke key){
        if (key != null) {
            switch (key.getKeyType()) {
                case ArrowRight:
                    element.getDirection().setDirectionID(0);
                    if(canMove(element))
                        element.moveRight();
                    break;
                case ArrowLeft:
                    element.getDirection().setDirectionID(2);
                    if(canMove(element))
                        element.moveLeft();
                    break;
                case ArrowUp:
                    element.getDirection().setDirectionID(3);
                    if(canMove(element) && !element.getJumpState())
                        element.moveUp();
                    break;
                case ArrowDown:
                    element.getDirection().setDirectionID(1);
                    if(canMove(element) && !element.getJumpState())
                        element.moveDown();
                    break;
                case Character:
                    if(key.getCharacter() == ' '){
                        if(canJump(element)){
                            element.setJumpState(true);
                            jump(element);
                        }
                    }
                    break;
            }
        }
        if(element.getJumpState()){
            jump(element);
        }
    }

    @Override
    public boolean canMove(Element element) {
        TextGraphics graphics = element.getGraphics();

        if (element.getDirection().getDirectionName() == Direction.DirectionEnum.NODIRECTION)
            return true;

        else if (element.getDirection().getDirectionName() == Direction.DirectionEnum.RIGHT) {
            if (!element.getJumpState())
                return Objects.equals(graphics.getCharacter(element.getPosition().getX() + 1, element.getPosition().getY() + 1).getCharacterString(), "p") || Objects.equals(graphics.getCharacter(element.getPosition().getX() + 1, element.getPosition().getY() + 1).getCharacterString(), "l");
            else return element.getPosition().getX() + 1 <= 48;
        }

        else if (element.getDirection().getDirectionName() == Direction.DirectionEnum.LEFT) {
            if(!element.getJumpState())
                return graphics.getCharacter(element.getPosition().getX() - 1, element.getPosition().getY() + 1).getCharacterString().equals("p") || graphics.getCharacter(element.getPosition().getX() - 1, element.getPosition().getY() + 1).getCharacterString().equals("l");
            else return element.getPosition().getX() - 1 >= 21;
        }

        else if (element.getDirection().getDirectionName() == Direction.DirectionEnum.DOWN && (graphics.getCharacter(element.getPosition().getX(), element.getPosition().getY() + 1).getCharacterString().equals("l") || graphics.getCharacter(element.getPosition().getX(), element.getPosition().getY() + 1).getCharacterString().equals(" ")))
            return true;

        else if (element.getDirection().getDirectionName() == Direction.DirectionEnum.UP) {
            for (Ladder l : ladders) {
                if (l.getPosition().equals(element.getPosition()))
                    return true;
            }
        }
        return false;
    }

    private boolean canJump(Element element) {
        for(Ladder l : ladders)
            if(l.getPosition().equals(element.getPosition())) return false;
        return (!element.getJumpState() && (element.getGraphics().getCharacter(element.getPosition().getX() - 1, element.getPosition().getY() + 1).getCharacterString().equals("p")
                || element.getGraphics().getCharacter(element.getPosition().getX() + 1, element.getPosition().getY() + 1).getCharacterString().equals("p")));
    }

    private void jump(Element element) {
        if(element.getJumpStrength() > 0) {
            element.moveUp();
            element.setJumpStrength(element.getJumpStrength() - 1);
        }
        else{
            if(!element.getGraphics().getCharacter(element.getPosition().getX(), element.getPosition().getY() + 1).getCharacterString().equals("p")
               && !element.getGraphics().getCharacter(element.getPosition().getX(), element.getPosition().getY() + 1).getCharacterString().equals("l"))
                element.moveDown();
            else {
                element.setJumpState(false);
                element.setJumpStrength(3);
            }
        }
    }




}
