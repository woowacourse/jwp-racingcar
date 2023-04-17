package racingcar.entity;

public class Player {

    private final Long id;
    private final Long gameId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public Player(final Long id, final Long gameId, final String name, final int position, final boolean isWinner) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public Long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
