package racingcar.repository.entity;

public class PositionEntity {

    private final Long id;
    private final Long gameId;
    private final Long userId;
    private final int position;

    public PositionEntity(final Long id, final Long gameId, final Long userId, final int position) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
        this.position = position;
    }

    public PositionEntity(final Long gameId, final Long userId, final int position) {
        this(null, gameId, userId, position);
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public long getUserId() {
        return userId;
    }

    public int getPosition() {
        return position;
    }
}
