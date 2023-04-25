package racingcar.entity;

public class PlayerEntity {

    private static final int DEFAULT_ID = -1;

    private final long id;
    private final long gameId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public PlayerEntity(final String name, final int position, final boolean isWinner) {
        this.id = DEFAULT_ID;
        this.gameId = DEFAULT_ID;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public PlayerEntity(final long id, final long gameId, final String name, final int position, final boolean isWinner) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean getIsWinner() {
        return isWinner;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", isWinner=" + isWinner +
                '}';
    }
}
