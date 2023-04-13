package racingcar.dao;

import java.time.LocalDateTime;

public class RacingHistory {
    private final int trialCount;
    private final LocalDateTime playTime;

    public RacingHistory(int trialCount, LocalDateTime playTime) {
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
