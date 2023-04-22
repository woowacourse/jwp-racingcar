package racingcar.entity;

import java.time.LocalDateTime;

public class RaceResultEntity {

    private final Long id;
    private final int trialCount;
    private final LocalDateTime createdAt;

    public RaceResultEntity(
            final Long id,
            final int trialCount,
            final LocalDateTime createdAt
    ) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public RaceResultEntity(
            final int trialCount,
            final LocalDateTime createdAt
    ) {
        this(null, trialCount, createdAt);
    }

    public Long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
