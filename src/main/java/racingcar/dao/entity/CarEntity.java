package racingcar.dao.entity;

public class CarEntity {

    private final Integer id;
    private final Integer gameId;
    private final String name;
    private final int position;

    public CarEntity(Integer gameId, String name, int position) {
        this.id = null;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public CarEntity(Integer id, Integer gameId, String name, int position) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
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
}
