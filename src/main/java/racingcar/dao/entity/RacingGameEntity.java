package racingcar.dao.entity;

import java.time.LocalDateTime;

public class RacingGameEntity {

    private final Long id;
    private final int trialCount;
    private final LocalDateTime createdTime;

    public RacingGameEntity(Long id, int trialCount, LocalDateTime createdTime) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdTime = createdTime;
    }

    public RacingGameEntity(int trialCount) {
        this(null, trialCount, LocalDateTime.now());
    }

    public int getTrialCount() {
        return trialCount;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
