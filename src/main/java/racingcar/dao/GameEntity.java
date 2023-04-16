package racingcar.dao;

import java.time.LocalTime;

public class GameEntity {

    private int gameId;
    private String winners;
    private int tryCount;
    private LocalTime createdAt;

    public GameEntity() {
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

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public void setCreatedAt(LocalTime createdAt) {
        this.createdAt = createdAt;
    }
}
