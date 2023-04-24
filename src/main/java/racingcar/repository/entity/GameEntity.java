package racingcar.repository.entity;

import java.time.LocalDateTime;

public class GameEntity {

    private final Long id;
    private final int trialCount;
    private final LocalDateTime createdAt;

    public GameEntity(final Long id, final int trialCount, final LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public GameEntity(final int trialCount) {
        this(null, trialCount, LocalDateTime.now());
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
