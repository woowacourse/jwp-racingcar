package racing.dao;

public class CarEntity {

    private final String name;
    private final int position;
    private final boolean isWinner;
    private final Long gameId;

    public CarEntity(String name, int position, boolean isWinner, Long gameId) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
        this.gameId = gameId;
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

    public Long getGameId() {
        return gameId;
    }

}
