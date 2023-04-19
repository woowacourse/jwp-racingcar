package racingcar.domain;


public class GameRound {
    public static final int MINIMUM_TRIAL_COUNT = 1;

    private final int totalRound;
    private int currRound;

    public GameRound(int totalRound) {
        if (totalRound < MINIMUM_TRIAL_COUNT) {
            throw new IllegalArgumentException("시도 횟수는 1이상을 입력해주세요.");
        }
        this.totalRound = totalRound;
        this.currRound = 0;
    }

    public void increaseRound() {
        this.currRound++;
    }

    public boolean isEnd() {
        return this.currRound == this.totalRound;
    }
}
