package racingcar.persistence.entity;

public class PlayerResultEntity {

    private final int id;
    private final String name;
    private final int position;
    private final Long gameResultId;

    public static PlayerResultEntity ofInward(
            final String name,
            final int position,
            final Long gameResultId
    ) {
        return new PlayerResultEntity(-1, name, position, gameResultId);
    }

    public static PlayerResultEntity ofOutward(
            final int id,
            final String name,
            final int position,
            final Long gameResultId
    ) {
        return new PlayerResultEntity(id, name, position, gameResultId);
    }

    public PlayerResultEntity(final int id, final String name, final int position, final Long gameResultId) {
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

    public Long getGameResultId() {
        return gameResultId;
    }
}
