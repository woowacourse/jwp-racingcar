package racingcar.dao;

public class Player {

    private Long id;
    private Long gameId;
    private String name;
    private int position;
    private boolean isWinner;

    private Player() {
    }

    public Player(final String name, final int position, final boolean isWinner) {
        this(0L, name, position, isWinner);
    }

    public Player(Long gameId, String name, int position, boolean isWinner) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public String getName() {
        return name;
    }

    public Long getGameId() {
        return gameId;
    }

    public int getPosition() {
        return position;
    }

    public boolean getIsWinner() {
        return isWinner;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setGameId(final Long gameId) {
        this.gameId = gameId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPosition(final int position) {
        this.position = position;
    }

    public void setIsWinner(final boolean winner) {
        isWinner = winner;
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
