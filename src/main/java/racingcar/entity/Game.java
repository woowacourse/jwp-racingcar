package racingcar.entity;

import java.time.LocalDateTime;

public class Game {

    private final Integer id;
    private final int trial;
    private final LocalDateTime createdAt;


    private Game(final Integer id, final int trial, final LocalDateTime createdAt) {
        this.id = id;
        this.trial = trial;
        this.createdAt = createdAt;
    }

    public static Game of(final int trial) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return new Game(null, trial, localDateTime);
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
