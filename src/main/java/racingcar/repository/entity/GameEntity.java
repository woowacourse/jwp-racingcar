package racingcar.repository.entity;

import java.time.LocalDateTime;

public class GameEntity {

    private final Long id;
    private final int trialCount;
    private final LocalDateTime lastModifiedTime;

    public GameEntity(final Long id, final int trialCount, final LocalDateTime lastModifiedTime) {
        this.id = id;
        this.trialCount = trialCount;
        this.lastModifiedTime = lastModifiedTime;
    }

    public GameEntity(final int trialCount) {
        this(null, trialCount, null);
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }
}
