package donkeyKongGame.elements;

import java.util.ArrayList;

public class Direction {

    public enum DirectionEnum {RIGHT, DOWN, LEFT,UP,NODIRECTION}
    private final ArrayList<DirectionEnum> directions = new ArrayList<>();
    private int directionID = 0;

    public Direction() {
        directions.add(DirectionEnum.RIGHT);
        directions.add(DirectionEnum.DOWN);
        directions.add(DirectionEnum.LEFT);
        directions.add(DirectionEnum.UP);
        directions.add(DirectionEnum.NODIRECTION);
    }

    public DirectionEnum getDirectionName() {
        return directions.get(directionID);
    }

    public int getDirectionID(){
        return directionID;
    }

    public void setDirectionID(int directionID){
        this.directionID = directionID;
    }

}
