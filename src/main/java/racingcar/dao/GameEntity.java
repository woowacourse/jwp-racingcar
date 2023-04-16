package racingcar.dao;

import java.time.LocalTime;

public class GameEntity {

    private int gameId;
    private final String winners;
    private final int tryCount;
    private final LocalTime createdAt;

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
