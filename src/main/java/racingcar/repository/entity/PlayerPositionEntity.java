package racingcar.repository.entity;

public class PlayerPositionEntity {

    private final Long id;
    private final Long gameId;
    private final Long userId;
    private final int position;

    public PlayerPositionEntity(final Long id, final Long gameId, final Long userId, final int position) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
        this.position = position;
    }

    public PlayerPositionEntity(final Long gameId, final Long userId, final int position) {
        this(0L, gameId, userId, position);
    }

    public Long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public int getPosition() {
        return position;
    }
}
