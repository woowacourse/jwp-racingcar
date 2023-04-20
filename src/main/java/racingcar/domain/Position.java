package racingcar.domain;

public class Position {
    private static final int INITIAL_POSITION = 0;

    private int position;

    private Position(int position) {
        this.position = position;
    }

    public static Position from(int position) {
        return new Position(position);
    }

    public static Position create() {
        return new Position(INITIAL_POSITION);
    }

    public void move() {
        position++;
    }

    public int getPosition() {
        return position;
    }
}
