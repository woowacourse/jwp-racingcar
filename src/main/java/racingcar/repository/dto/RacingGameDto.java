package racingcar.repository.dto;

import java.time.LocalDateTime;

public class RacingGameDto {
    private final int id;
    private final String winners;
    private final LocalDateTime createdAt;
    private final int trial;

    public RacingGameDto(final int id, final String winners, final LocalDateTime createdAt, final int trial) {
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
