package racingcar.entity;

public class PlayerResultEntity {

    private final int id;
    private final String name;
    private final int position;
    private final int gameResultId;

    public PlayerResultEntity(final int id, final String name, final int position, final int gameResultId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameResultId = gameResultId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getGameResultId() {
        return gameResultId;
    }
}
