package donkeyKongGame.elements;
import com.googlecode.lanterna.TextColor;
import donkeyKongGame.movement.MoveStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Mario extends Element {

    private boolean alive = true;
    private Hammer hammer = null;

    public Mario(int x, int y,MoveStrategy moveStrategy, TextGraphics graphics) {
        super(x,y,moveStrategy, graphics);
    }

    @Override
    public void display() {
        if (hasHammer())
            graphics.setForegroundColor(TextColor.Factory.fromString("#f69e51"));
        else
            graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        if (direction.getDirectionName() == Direction.DirectionEnum.RIGHT)
            graphics.putString(getPosition().getX(), getPosition().getY(), "m");
        else if (direction.getDirectionName() == Direction.DirectionEnum.LEFT)
            graphics.putString(getPosition().getX(), getPosition().getY(), "n");
        else
            graphics.putString(getPosition().getX(), getPosition().getY(), "i");
    }

    public boolean isAlive() {
        return alive;
    }

    public void setDead() {
        alive = false;
    }

    public boolean hasHammer() {
        return hammer != null;
    }

    public boolean canDropHammer(){
        return hammer.didTimeEnded();
    }

    public void pickHammer(Hammer hammer) {
        this.hammer = hammer;
        hammer.startTime();
    }

    public void dropHammer(){
        hammer = null;
    }
}