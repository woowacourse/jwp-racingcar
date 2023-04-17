package racingcar.dao.entity;

import java.time.LocalDateTime;

public class SelectGameEntity {

    private final int gameId;
    private final LocalDateTime createdAt;
    private final int tryCount;

    public SelectGameEntity(final int gameId, final LocalDateTime createdAt, final int tryCount) {
        this.gameId = gameId;
        this.createdAt = createdAt;
        this.tryCount = tryCount;
    }

    public int getGameId() {
        return gameId;
    }

    public int getTryCount() {
        return tryCount;
    }
}
