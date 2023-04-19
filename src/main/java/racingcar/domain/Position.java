package racingcar.domain;

import racingcar.strategy.RacingNumberGenerator;

public class Position implements Comparable<Position> {

    private static final int START_POSITION = 0;
    private static final int MOVABLE_VALUE = 4;

    private int position;

    public Position() {
        this.position = START_POSITION;
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

    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(Position otherPosition) {
        return this.position - otherPosition.position;
    }
}
