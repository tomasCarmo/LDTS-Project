package donkeyKongGame.elements;

import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public abstract class Element {

    private final Position position;
    protected TextGraphics graphics;
    private final MoveStrategy moveStrategy;
    protected Direction direction = new Direction();
    protected boolean isJumping = false;
    protected double jumpStrength = 3;

    public Element(int x, int y,MoveStrategy moveStrategy, TextGraphics graphics) {
        this.position = new Position(x,y);
        this.moveStrategy = moveStrategy;
        this.graphics = graphics;
    }

    public void move(KeyStroke key) {
        this.moveStrategy.move(this,key);
    }

    public Position getPosition() {
        return position;
    }

    public void moveUp() {
        this.position.moveUp();
    }

    public void moveRight() {
        this.position.moveRight();
    }

    public void moveDown() {
        this.position.moveDown();
    }

    public void moveLeft() {
        this.position.moveLeft();
    }

    public void noMove() {}

    public abstract void display();

    public Direction getDirection(){
        return direction;
    }

    public TextGraphics getGraphics(){
        return graphics;
    }

    public MoveStrategy getMoveStrategy(){
        return moveStrategy;
    }

    public boolean getJumpState() {
        return isJumping;
    }

    public void setJumpState(boolean jump) {
        isJumping = jump;
    }

    public double getJumpStrength() {
        return jumpStrength;
    }

    public void setJumpStrength(double jumpStrength) {
        this.jumpStrength = jumpStrength;
    }
}