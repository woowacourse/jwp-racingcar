package racingcar.entity;

import java.time.LocalDateTime;

public class Game {

    private final Integer id;
    private final int trial;
    private final LocalDateTime createdAt;

    public Game(final Integer id, final int trial, final LocalDateTime createdAt) {
        this.id = id;
        this.trial = trial;
        this.createdAt = createdAt;
    }

    public Game(final int trial) {
        this(null, trial, LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public int getTrial() {
        return trial;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
