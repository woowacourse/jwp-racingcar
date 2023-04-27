package racingcar.entity;

public class PlayersInfo {
    private final Integer id;
    private final String name;
    private final Integer position;
    private final Integer playResultId;

    public PlayersInfo(Integer id, String name, Integer position, Integer playResultId) {
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
