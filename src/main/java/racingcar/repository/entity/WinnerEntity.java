package racingcar.repository.entity;

public class WinnerEntity {

    private final Long id;
    private final Long gameId;
    private final Long playerId;

    public WinnerEntity(final Long id, final Long gameId, final Long playerId) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
    }

    public WinnerEntity(final Long gameId, final Long playerId) {
        this(null, gameId, playerId);
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public long getPlayerId() {
        return playerId;
    }
}
