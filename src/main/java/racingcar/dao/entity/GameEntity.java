package racingcar.dao.entity;

import racingcar.domain.RacingGame;

import java.time.LocalTime;

public class GameEntity {


    private static final Integer DEFAULT_ID = null;

    private Integer gameId;
    private Integer tryCount;
    private LocalTime createdAt;


    public GameEntity(final Integer gameId, final Integer tryCount, final LocalTime createdAt) {
        this.gameId = gameId;
        this.tryCount = tryCount;
        this.createdAt = createdAt;
    }

    public static GameEntity from (RacingGame racingGame) {
        return new GameEntity(DEFAULT_ID, racingGame.getTryCount(), racingGame.getCreated_at());
    }

    public int getGameId() {
        return gameId;
    }

    public int getTryCount() {
        return tryCount;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }
}
