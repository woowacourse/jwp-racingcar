package racingcar.domain.car;

import java.util.Objects;

public class Position {
    private static final int DEFAULT_START_POSITION = 0;

    private int value = DEFAULT_START_POSITION;

    public Position() {
    }

    public Position(int step) {
        this.value = step;
    }

    public void add(int step) {
        this.value += step;
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
        Position position = (Position) o;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
