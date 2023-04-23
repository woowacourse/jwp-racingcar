package racingcar.dao.entity;

import java.time.LocalDateTime;

public class GameEntity {

    private final GameId gameId;
    private final int trialCount;
    private final LocalDateTime createdAt;

    public GameEntity(final Integer gameId, final int trialCount) {
        this(gameId, trialCount, LocalDateTime.now());
    }

    public GameEntity(final Integer gameId, final int trialCount, final LocalDateTime createdAt) {
        this.gameId = new GameId(gameId);
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public GameId getGameId() {
        return gameId;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
