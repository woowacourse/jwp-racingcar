package racingcar.persistence.entity;

import java.util.Date;

public class GameResultEntity {

    private final long id;
    private final int trialCount;
    private final String winners;
    private final Date createdAt;

    public GameResultEntity(long id, int trialCount, String winners, Date createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }
}
