package racingcar.entity;

import java.time.LocalDateTime;

public class GameEntity {

    private final Integer id;
    private final int trialCount;
    private final LocalDateTime createdAt;

    public GameEntity(Integer id, int trialCount, LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public static GameEntity from(int trialCount) {
        return new GameEntity(null, trialCount, null);
    }

    public Integer getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
