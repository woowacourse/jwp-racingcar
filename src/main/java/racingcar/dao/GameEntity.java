package racingcar.dao;

import java.time.LocalTime;

public class GameEntity {

    public final static String WINNER_DELIMITER = ",";

    private int gameId;
    private String winners;
    private int tryCount;
    private LocalTime createdAt;

    public GameEntity() {
    }

    public GameEntity(int gameId, String winners) {
        this.gameId = gameId;
        this.winners = winners;
    }

    public GameEntity(String winners, int tryCount, LocalTime createdAt) {
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
