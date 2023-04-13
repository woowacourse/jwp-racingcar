package racingcar.domain;

import java.sql.Timestamp;

public class PlayResult {
    private final Long id;
    private final int trialCount;
    private final String winners;
    private final Timestamp createdAt;

    private PlayResult(Long id, int trialCount, String winners, Timestamp createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public static PlayResult of(int trialCount, String winners, Timestamp createdAt) {
        return new PlayResult(null, trialCount, winners, createdAt);
    }

    public static PlayResult of(Long id, int trialCount, String winners, Timestamp createdAt) {
        return new PlayResult(id, trialCount, winners, createdAt);
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
