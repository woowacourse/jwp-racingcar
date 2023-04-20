package racingcar.model;

public class Position {
    private static final int CAR_MOVE_LENGTH = 1;

    private int position;

    public Position(int position) {
        this.position = position;
    }

    public void moveCarPosition() {
        position += CAR_MOVE_LENGTH;
    }

    public boolean checkEqual(Position comparator) {
        return this.position == comparator.position;
    }

    public int getPosition() {
        return this.position;
    }
}
