package racingcar.entity;

import racingcar.controller.ApplicationType;

import java.util.Date;

public class GameEntity {

    private static final int DEFAULT_ID = -1;
    private static final Date DEFAULT_CREATE_AT = null;

    private final long id;
    private final int trialCount;
    private final Date createdAt;
    private final ApplicationType applicationType;

    public GameEntity(final long id, final int trialCount, final Date createdAt, final ApplicationType applicationType) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
        this.applicationType = applicationType;
    }

    public GameEntity(final int trialCount, final ApplicationType applicationType) {
        this.id = DEFAULT_ID;
        this.trialCount = trialCount;
        this.createdAt = DEFAULT_CREATE_AT;
        this.applicationType = applicationType;
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", trialCount=" + trialCount +
                ", createdAt=" + createdAt +
                ", applicationType=" + applicationType +
                '}';
    }
}
