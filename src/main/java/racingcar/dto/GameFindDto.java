package racingcar.dto;

import racingcar.controller.ApplicationType;

import java.util.Date;

public class GameFindDto {

    private final long id;
    private final int trialCount;
    private final Date createdAt;
    private final ApplicationType applicationType;

    public GameFindDto(long id, int trialCount, Date createdAt, ApplicationType applicationType) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
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
        return "GameFindDto{" +
                "id=" + id +
                ", trialCount=" + trialCount +
                ", createdAt=" + createdAt +
                '}';
    }
}
