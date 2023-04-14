package racingcar.domain;

import java.util.Objects;

public final class Position {
    private static final int INITIAL_POSITION = 0;

    private int position;

    private Position(int position) {
        this.position = position;
    }

    public Position(final Position position) {
        this.position = position.getPosition();
    }

    public static Position of(final int position) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position other = (Position) o;
        return getPosition() == other.getPosition();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
