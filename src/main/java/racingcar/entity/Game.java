package racingcar.entity;

import java.time.LocalDateTime;

public class Game {

    private long id;
    private int trialCount;
    private final String winners;
    private final LocalDateTime createdAt;

    public Game(final long id, final int trialCount, final String winners, LocalDateTime createdAt) {
        this.id = id;
        this.trialCount =trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public Game(final int trialCount, final String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = LocalDateTime.now();
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
