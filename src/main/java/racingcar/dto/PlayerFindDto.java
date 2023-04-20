package racingcar.dto;

public class PlayerFindDto {

    private final long id;
    private final long gameId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public PlayerFindDto(long id, long gameId, String name, int position, boolean isWinner) {
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
        return "PlayerFindDto{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", isWinner=" + isWinner +
                '}';
    }
}
