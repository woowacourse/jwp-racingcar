package racingcar.dao.entity;

import java.time.LocalTime;

public class GameEntity {

    private int gameId;
    private String winners;
    private int tryCount;
    private LocalTime createdAt;

    public GameEntity(final String winners, final int tryCount, final LocalTime createdAt) {
        this.winners = winners;
        this.tryCount = tryCount;
        this.createdAt = createdAt;
    }

    public int getGameId() {
        return gameId;
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
