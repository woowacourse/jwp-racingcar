package racingcar.dao;

import java.time.LocalDateTime;

public class RacingGameHistory {
    private final int trialCount;
    private final LocalDateTime playTime;

    public RacingGameHistory(int trialCount, LocalDateTime playTime) {
        this.trialCount = trialCount;
        this.playTime = playTime;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getPlayTime() {
        return playTime;
    }
}
