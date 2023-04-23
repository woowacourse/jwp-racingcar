package racingcar.dao.entity;

public class CarEntity {

    private final Integer id;
    private final Integer gameId;
    private final String name;
    private final Integer position;

    public CarEntity(Integer gameId, String name, Integer position) {
        this(null, gameId, name, position);
    }

    public CarEntity(Integer id, Integer gameId, String name, Integer position) {
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

    public Integer getPosition() {
        return position;
    }
}
