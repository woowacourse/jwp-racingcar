package racingcar.domain;

public enum GameResult {
    WIN(1),
    LOSE(0);

    private final int value;

    GameResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
