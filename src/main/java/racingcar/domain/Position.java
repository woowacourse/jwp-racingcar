package racingcar.domain;

public class Position {

    private int position;

    public Position(final int position) {
        validatePosition(position);
        this.position = position;
    }

    private void validatePosition(final int position) {
        validatePositionIsNotNegative(position);
    }

    private void validatePositionIsNotNegative(final int position) {
        if (position < 0) {
            throw new IllegalArgumentException("자동차의 위치는 음수일 수 없습니다.");
        }
    }

    public void moveForward() {
        position++;
    }

    public int getPosition() {
        return position;
    }
}
