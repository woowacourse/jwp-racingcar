package racingcar.model;

public class TryCount {
    private final int value;

    public TryCount(final int value) {
        validateSize(value);
        this.value = value;
    }

    private void validateSize(final int value) {
        if (value < 1) {
            throw new IllegalArgumentException("게임 시도 횟수는 0보다 커야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
