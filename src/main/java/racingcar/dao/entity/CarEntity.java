package racingcar.dao.entity;

import java.util.Objects;

public class CarEntity {

    private final Long playId;
    private final String name;
    private final int position;

    public CarEntity(final Long playId, final String name, final int position) {
        this.playId = playId;
        this.name = name;
        this.position = position;
    }

    public CarEntity(final String name, final int position) {
        this(null, name, position);
    }

    public Long getPlayId() {
        return playId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarEntity carEntity = (CarEntity) o;
        return position == carEntity.position && Objects.equals(playId, carEntity.playId) && Objects.equals(
                name, carEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playId, name, position);
    }
}
