package racingcar.domain;

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
            throw new IllegalStateException("위치는 음수일 수 없습니다.");
        }
    }

    public void moveForward() {
        this.position++;
    }

    public int getPosition() {
        return this.position;
    }
}
