package racingcar.entity;

import racingcar.controller.ApplicationType;

import java.util.Date;

public class Game {

    private final long id;
    private final int trialCount;
    private final Date createdAt;
    private final ApplicationType applicationType;

    public Game(long id, int trialCount, Date createdAt, ApplicationType applicationType) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
        this.applicationType = applicationType;
    }

    public Game(int trialCount, ApplicationType applicationType) {
        this.id = -1;
        this.trialCount = trialCount;
        this.createdAt = null;
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
