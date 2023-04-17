package racingcar.dao;

import java.time.LocalDateTime;

public class RacingHistory {

    private final long id;
    private final int trialCount;
    private final LocalDateTime playTime;

    public RacingHistory(long id, int trialCount, LocalDateTime playTime) {
        this.id = id;
        this.trialCount = trialCount;
        this.playTime = playTime;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getPlayTime() {
        return playTime;
    }

    public long getId() {
        return id;
    }

}
