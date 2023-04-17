package racingcar.dao;

import java.time.LocalDateTime;

public class RacingGameHistory {

    private final long id;
    private final int trialCount;
    private final LocalDateTime playTime;

    public RacingGameHistory(long id, int trialCount, LocalDateTime playTime) {
        this.id = id;
        this.trialCount = trialCount;
        this.playTime = playTime;
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getPlayTime() {
        return playTime;
    }
}
