package racingcar.repository.entity;

public class PositionEntity {

    private final long id;
    private final long gameId;
    private final long userId;
    private final int position;

    public PositionEntity(final long id, final long gameId, final long userId, final int position) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
        this.position = position;
    }

    public PositionEntity(final long gameId, final long userId, final int position) {
        this(0, gameId, userId, position);
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
