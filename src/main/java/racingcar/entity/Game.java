package racingcar.entity;

import java.time.LocalDateTime;

public class Game {

    private long id;
    private final int trialCount;
    private final String winners;
    private final LocalDateTime createdAt;

    public Game(final int trialCount, final String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = LocalDateTime.now();
    }

    public Game(final long id, final Game game) {
        this.id = id;
        this.trialCount = game.trialCount;
        this.winners = game.winners;
        this.createdAt = game.createdAt;
    }

    public long getId() {
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
