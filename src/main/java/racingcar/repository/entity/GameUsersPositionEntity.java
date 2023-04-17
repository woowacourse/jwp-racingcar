package racingcar.repository.entity;

public class GameUsersPositionEntity {

    private final Long id;
    private final Long gameId;
    private final Long usersId;
    private final int position;

    public GameUsersPositionEntity(final long gameId, final long usersId, final int position) {
        this.id = null;
        this.gameId = gameId;
        this.usersId = usersId;
        this.position = position;
    }

    public GameUsersPositionEntity(final long id, final long gameId, final long usersId, final int position) {
        this.id = id;
        this.gameId = gameId;
        this.usersId = usersId;
        this.position = position;
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

    public int getPosition() {
        return position;
    }
}
