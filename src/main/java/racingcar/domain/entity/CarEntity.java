package racingcar.domain.entity;

public class CarEntity {

    private int id;
    private int gameId;
    private final String name;
    private final int position;
    private final boolean isWin;

    public CarEntity(String name, int position, boolean isWin) {
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public CarEntity(final int id, final int gameId, final String name, final int position, final boolean isWin) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public int getGameId() {
        return gameId;
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
}
