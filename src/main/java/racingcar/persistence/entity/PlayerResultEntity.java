package racingcar.persistence.entity;

public class PlayerResultEntity {

    private final Integer id;
    private final String name;
    private final Integer position;
    private final Integer gameResultId;

    public static PlayerResultEntity ofInward(
            final String name,
            final Integer position,
            final Integer gameResultId
    ) {
        return new PlayerResultEntity(null, name, position, gameResultId);
    }

    public static PlayerResultEntity ofOutward(
            final Integer id,
            final String name,
            final Integer position,
            final Integer gameResultId
    ) {
        return new PlayerResultEntity(id, name, position, gameResultId);
    }

    private PlayerResultEntity(final Integer id, final String name, final Integer position, final Integer gameResultId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameResultId = gameResultId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getGameResultId() {
        return gameResultId;
    }
}
