package racingcar.domain;

import racingcar.exception.PositionInvalidException;

public class Position {

    private int position;

    public Position(int position) {
        validatePosition(position);
        this.position = position;
    }

    private void validatePosition(int position) {
        validatePositionIsNotNegative(position);
    }

    private void validatePositionIsNotNegative(int position) {
        if (position < 0) {
            throw new PositionInvalidException();
        }
    }

    public void moveForward() {
        this.position++;
    }

    public int getPosition() {
        return this.position;
    }
}
