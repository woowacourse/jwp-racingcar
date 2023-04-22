package racingcar.persistence.entity;

import java.time.LocalDateTime;

public class GameResultEntity {

    private final Integer id;
    private final Integer trialCount;
    private final String winners;
    private final LocalDateTime createdAt;

    public static GameResultEntity ofInward(
            final Integer trialCount,
            final String winners
    ) {
        return new GameResultEntity(null, trialCount, winners, null);
    }

    public static GameResultEntity ofOutward(
            final Integer id,
            final Integer trialCount,
            final String winners,
            final LocalDateTime createdAt
    ) {
        return new GameResultEntity(id, trialCount, winners, createdAt);
    }

    private GameResultEntity(final Integer id, final Integer trialCount, final String winners, final LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
