package racingcar.dto;

import java.util.Date;

public class GameFindDto {

    private final long id;
    private final int trialCount;
    private final Date createdAt;

    public GameFindDto(long id, int trialCount, Date createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
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
}
