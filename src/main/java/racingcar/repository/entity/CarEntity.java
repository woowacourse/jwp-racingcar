package racingcar.repository.entity;

public class CarEntity {

    private final Long id;
    private final Long gameId;
    private final Long playerId;
    private final int position;

    public CarEntity(final Long id, final Long gameId, final Long playerId, final int position) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.position = position;
    }

    public CarEntity(final Long gameId, final Long playerId, final int position) {
        this(null, gameId, playerId, position);
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

    public int getPosition() {
        return position;
    }
}
