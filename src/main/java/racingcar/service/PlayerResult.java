package racingcar.service;

import java.util.Objects;

public class PlayerResult {

    private int id;
    private final int playResultId;
    private final String name;
    private final int position;

    public PlayerResult(final int playResultId, final String name, final int position) {
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public PlayerResult(final int id, final int playResultId, final String name, final int position) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPlayResultId() {
        return playResultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerResult that = (PlayerResult)o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PlayerResult{" +
            "id=" + id +
            ", playResultId=" + playResultId +
            ", name='" + name + '\'' +
            ", position=" + position +
            '}';
    }
}
