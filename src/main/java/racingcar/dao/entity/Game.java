package racingcar.dao.entity;

import java.time.LocalDateTime;

public class Game {
    private final Long id;
    private final int trialCount;
    private final LocalDateTime date;

    public Game(int trialCount) {
        this.id = null;
        this.trialCount = trialCount;
        this.date = null;
    }

    public Long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
