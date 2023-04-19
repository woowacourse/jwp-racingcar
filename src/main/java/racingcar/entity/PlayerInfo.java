package racingcar.entity;

import java.sql.Timestamp;

public class PlayerInfo {
    Integer id;
    String name;
    Integer position;
    Integer playResultId;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(final Integer position) {
        this.position = position;
    }

    public Integer getPlayResultId() {
        return playResultId;
    }

    public void setPlayResultId(final Integer playResultId) {
        this.playResultId = playResultId;
    }
}
