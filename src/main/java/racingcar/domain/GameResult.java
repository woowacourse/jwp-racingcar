package racingcar.domain;

public enum GameResult {
    WIN(true),
    LOSE(false);

    private final boolean value;

    GameResult(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
