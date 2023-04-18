package racing.dao;

public class CarEntity {

    private final String name;
    private final int step;
    private final boolean isWinner;
    private final Long gameId;

    public CarEntity(String name, int step, boolean isWinner, Long gameId) {
        this.name = name;
        this.step = step;
        this.isWinner = isWinner;
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public int getStep() {
        return step;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public Long getGameId() {
        return gameId;
    }

}
