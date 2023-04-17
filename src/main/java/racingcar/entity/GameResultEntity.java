package racingcar.entity;

import java.time.LocalDateTime;

public class GameResultEntity {

    private final int id;
    private final int trialCount;
    private final String winners;
    private final LocalDateTime createdAt;

    public GameResultEntity(final int id, final int trialCount, final String winners, final LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
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
