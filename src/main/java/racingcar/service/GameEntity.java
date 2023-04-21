package racingcar.service;

import java.time.LocalDateTime;

public class GameEntity {
    private int id;
    private final String winners;
    private final int trial;
    private LocalDateTime playedTime;

    public GameEntity(final String winners, final int trial) {
        this.winners = winners;
        this.trial = trial;
        this.playedTime = LocalDateTime.now();
    }

    public GameEntity(final int id, final String winners, final int trial) {
        this.id = id;
        this.winners = winners;
        this.trial = trial;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getTrial() {
        return trial;
    }

    public LocalDateTime getPlayedTime() {
        return playedTime;
    }
}
