package racingcar.repository.entity;

import java.sql.Timestamp;

public class GameEntity {

    private final Long id;
    private final int trialCount;
    private final Timestamp lastModifiedTime;

    public GameEntity(final Long id, final int trialCount, final Timestamp lastModifiedTime) {
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

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }
}
