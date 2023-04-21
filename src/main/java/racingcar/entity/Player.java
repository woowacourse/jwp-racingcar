package racingcar.entity;

public class Player {

    private static final int DEFAULT_ID = -1;

    private final long id;
    private final long gameId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public Player(final String name, final int position, final boolean isWinner) {
        this.id = DEFAULT_ID;
        this.gameId = DEFAULT_ID;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public Player(final long id, final long gameId, final String name, final int position, final boolean isWinner) {
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
        return "PlayerSaveDto{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", isWinner=" + isWinner +
                '}';
    }
}
