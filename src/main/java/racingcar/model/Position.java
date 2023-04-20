package racingcar.model;

import racingcar.strategy.RacingNumberGenerator;

import java.util.Objects;

public class Position implements Comparable<Position> {

    private static final int MOVABLE_VALUE = 4;

    private int position = 0;

    public Position(final int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void move(RacingNumberGenerator generator) {
        if (isMovable(generator)) {
            position++;
        }
    }

    private boolean isMovable(RacingNumberGenerator generator) {
        return generator.generate() >= MOVABLE_VALUE;
    }

    public boolean isSamePosition(Position otherPosition) {
        return this.position == otherPosition.position;
    }

    @Override
    public int compareTo(Position otherPosition) {
        return this.position - otherPosition.position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
