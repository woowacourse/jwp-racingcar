package racingcar.domain;

import java.util.Objects;

public final class Position {
    public static final Position ZERO = new Position(0);
    private static final int MOVEMENT = 1;

    private final int value;

    public Position(final int value) {
        this.value = value;
    }

    public Position move() {
        return new Position(this.value + MOVEMENT);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position1 = (Position) o;
        return getValue() == position1.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
