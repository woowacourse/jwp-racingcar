package racingcar.repository.entity;

public class GameWinnerEntity {

    private final Long id;
    private final Long gameId;
    private final Long userId;

    public GameWinnerEntity(final Long id, final Long gameId, final Long userId) {
        this.id = id;
        this.gameId = gameId;
        this.userId = userId;
    }

    public GameWinnerEntity(final Long gameId, final Long userId) {
        this(0L, gameId, userId);
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
}
