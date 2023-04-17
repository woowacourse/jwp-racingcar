package racingcar.repository.entity;

public class WinnerEntity {

    private final Long id;
    private final Long gameId;
    private final Long userId;

    public WinnerEntity(final Long id, final Long gameId, final Long userId) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
    }

    public WinnerEntity(final Long gameId, final Long userId) {
        this(null, gameId, userId);
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
}
