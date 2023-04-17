package racingcar.domain;

public class Position {
    private static final int INITIAL_POSITION = 0;

    private int position;

    private Position() {
        this.position = INITIAL_POSITION;
    }

    public static Position create() {
        return new Position();
    }

    public void move() {
        position++;
    }

    public int getPosition() {
        return position;
    }
}
