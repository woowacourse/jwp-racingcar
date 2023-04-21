package racingcar.domain;

public class GameRound {

    private static final String BOUNDARY_EXCEPTION = "[ERROR] 시도할 게임 회수는 양의 정수이어야 합니다.";

    private final int totalRound;
    private int currRound;

    public GameRound(int totalRound) {
        validate(totalRound);
        this.totalRound = totalRound;
        this.currRound = 0;
    }

    private void validate(int inputRound) {
        if (inputRound <= 0) {
            throw new IllegalArgumentException(BOUNDARY_EXCEPTION);
        }
    }

    public void increaseRound() {
        this.currRound++;
    }

    public boolean isPlayable() {
        return this.currRound != this.totalRound;
    }
}
