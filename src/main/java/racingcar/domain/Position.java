package racingcar.domain;

import java.util.Objects;

public class Position {
    private final int position;

    private Position(int position) {
        this.position = position;
    }

    public static Position of(int position) {
        return new Position(position);
    }

    public Position plus() {
        return Position.of(position + 1);
    }

    public int getValue() {
        return position;
    }

    public boolean isValueOf(int position) {
        return Objects.equals(this.position, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Position{" +
                "position=" + position +
                '}';
    }
}
