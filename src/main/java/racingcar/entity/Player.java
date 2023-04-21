package racingcar.entity;

public class Player {

    private final long id;
    private final long gameId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public Player(final String name, final int position, final boolean isWinner) {
        this.id = -1;
        this.gameId = -1;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public Player(long id, long gameId, String name, int position, boolean isWinner) {
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
