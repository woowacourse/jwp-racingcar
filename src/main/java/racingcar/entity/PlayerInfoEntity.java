package racingcar.entity;

public class PlayerInfoEntity {
    private final Integer id;
    private final String name;
    private final Integer position;
    private final Integer playResultId;

    public PlayerInfoEntity(final Integer id, final String name, final Integer position, final Integer playResultId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.playResultId = playResultId;
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

    public Integer getPlayResultId() {
        return playResultId;
    }
}
