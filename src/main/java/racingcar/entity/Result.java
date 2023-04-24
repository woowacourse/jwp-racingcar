package racingcar.entity;

import java.time.LocalDateTime;

public class Result {

    private final long id;
    private final int trialCount;
    private final String winners;
    private final LocalDateTime createdAt;

    public Result(long id, int trialCount, String winners, LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
