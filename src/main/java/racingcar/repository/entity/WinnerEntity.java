package racingcar.repository.entity;

public class WinnerEntity {

    private final Long id;
    private final Long gameId;
    private final Long userId;

    public WinnerEntity(final long gameId, final long userId) {
        this.id = null;
        this.gameId = gameId;
        this.userId = userId;
    }

    public WinnerEntity(final long id, final long gameId, final long userId) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
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
