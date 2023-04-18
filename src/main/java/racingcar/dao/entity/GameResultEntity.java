package racingcar.dao.entity;

import java.time.LocalTime;

public class GameResultEntity {
    private final int id;
    private final int trialCount;
    private final LocalTime createdAt;

    private GameResultEntity(int id, int trialCount, LocalTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public static GameResultEntity of(int id, int trialCount, LocalTime createdAt){
        return new GameResultEntity(id,trialCount,createdAt);
    }

    public int getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }
}
