package racingcar.model;

public class Position {
    private static final int CAR_MOVE_LENGTH = 1;

    private int location;

    public Position(int location) {
        this.location = location;
    }

    public void moveCarLocation() {
        location += CAR_MOVE_LENGTH;
    }

    public boolean checkEqual(Position comparator) {
        return this.location == comparator.location;
    }

    public int getPosition() {
        return this.location;
    }
}
