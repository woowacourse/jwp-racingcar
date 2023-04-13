package racingcar.repository.mapper;

import java.time.LocalDateTime;

public class RacingGameInfo {

    private int id;
    private String winners;
    private LocalDateTime createdAt;
    private int trial;

    public RacingGameInfo(final int id, final String winners, final LocalDateTime createdAt, final int trial) {
        this.id = id;
        this.winners = winners;
        this.createdAt = createdAt;
        this.trial = trial;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getTrial() {
        return trial;
    }
}
