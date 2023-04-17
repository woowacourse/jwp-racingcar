package racingcar.entity;

import java.time.LocalDateTime;

public class GameEntity {
    private final Integer id;
    private final int trial;
    private final LocalDateTime createdAt;

    public GameEntity(final int trial) {
        this(null, trial, LocalDateTime.now());
    }

    public GameEntity(final Integer id, final int trial, final LocalDateTime createdAt) {
        this.id = id;
        this.trial = trial;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public int getTrial() {
        return trial;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
