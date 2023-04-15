package racingcar.repository.entity;

public class GameWinUsersEntity {

    private final long id;
    private final long gameId;
    private final long usersId;

    public GameWinUsersEntity(final long gameId, final long usersId) {
        this.id = 0;
        this.gameId = gameId;
        this.usersId = usersId;
    }

    public GameWinUsersEntity(final long id, final long gameId, final long usersId) {
        this.id = id;
        this.gameId = gameId;
        this.usersId = usersId;
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public long getUsersId() {
        return usersId;
    }
}
