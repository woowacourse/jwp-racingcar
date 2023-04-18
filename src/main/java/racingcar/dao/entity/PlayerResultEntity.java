package racingcar.dao.entity;

public class PlayerResultEntity {
    private final int id;
    private final String name;
    private final int position;
    private final int gameId;

    private PlayerResultEntity(int id, String name, int position, int gameId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public static PlayerResultEntity of(int id, String name, int position, int gameId) {
        return new PlayerResultEntity(id, name, position, gameId);
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

    public int getGameId() {
        return gameId;
    }
}
