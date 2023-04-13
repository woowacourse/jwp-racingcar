package racingcar.dto;

import java.time.LocalTime;

public class GameDto {
    private final String winners;
    private final int tryCount;
    private LocalTime createdAt;

    public GameDto(final String winners, final int tryCount, final LocalTime createdAt) {
        this.winners = winners;
        this.tryCount = tryCount;
        this.createdAt = createdAt;
    }

    public String getWinners() {
        return winners;
    }

    public int getTryCount() {
        return tryCount;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }
}
