package racingcar.dao.entity;

public class CarEntity {

    private final String name;
    private final int position;
    private final boolean isWin;
    private final Long gameId;

    public CarEntity(String name, int position, boolean isWin, Long gameId) {
        this.name = name;
        this.position = position;
        this.isWin = isWin;
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWin() {
        return isWin;
    }

    public Long getGameId() {
        return gameId;
    }
}
