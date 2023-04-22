package racingcar.entity;

import java.sql.Timestamp;

public class GameEntity {
    private final Long id;
    private final int trialCount;
    private final Timestamp createdAt;

    private GameEntity(Long id, int trialCount, Timestamp createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public static GameEntity from(int trialCount) {
        return new GameEntity(null, trialCount, null);
    }

    public static GameEntity of(Long id, int trialCount, Timestamp createdAt) {
        return new GameEntity(id, trialCount, createdAt);
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
