package racingcar.dao.entity;

public class CarEntity {

    private final Integer id;
    private final Integer gameId;
    private final String name;
    private final int position;
    private final boolean isWin;

    public CarEntity(Integer gameId, String name, int position, boolean isWin) {
        this.id = null;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public CarEntity(Integer id, Integer gameId, String name, int position, boolean isWin) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGameId() {
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
