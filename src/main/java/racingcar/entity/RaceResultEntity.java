package racingcar.entity;

import java.time.LocalDateTime;

public class RaceResultEntity {

    private final Long id;
    private final int trialCount;
    private final String winners;
    private final LocalDateTime createdAt;

    private RaceResultEntity(final Long id, final int trialCount,
                             final String winners, final LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public RaceResultEntity(final int trialCount, final String winners,
                            final LocalDateTime createdAt) {
        this(null, trialCount, winners, createdAt);
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
