package racingcar.entity;

public class CarEntity {

    private final int gameId;
    private final String name;
    private final int position;
    private int id;

    public CarEntity(final int gameId, final String name, final int position) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public CarEntity(final int id, final int gameId, final String name, final int position) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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

}
