package racingcar.repository.entity;

public class GameWinnerEntity {

    private final long id;
    private final long gameId;
    private final long userId;

    public GameWinnerEntity(final long id, final long gameId, final long userId) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
    }

    public GameWinnerEntity(final long gameId, final long userId) {
        this(0, gameId, userId);
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
