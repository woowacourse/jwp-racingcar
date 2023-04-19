package racingcar.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class RacingGameEntity {

    private Integer id;
    private List<CarEntity> carEntities;
    private final Integer count;
    private LocalDateTime createdAt;

    public RacingGameEntity(final List<CarEntity> carEntities, final Integer count) {
        this.carEntities = carEntities;
        this.count = count;
    }

    public RacingGameEntity(final Integer id, final Integer count, final LocalDateTime createdAt) {
        this.id = id;
        this.count = count;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public List<CarEntity> getCarEntities() {
        return carEntities;
    }

    public void setCarEntities(final List<CarEntity> carEntities) {
        this.carEntities = carEntities;
    }

    public Integer getCount() {
        return count;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final RacingGameEntity otherEntity = (RacingGameEntity) other;

        if (id == null || otherEntity.id == null) {
            return false;
        }

        return Objects.equals(carEntities, otherEntity.carEntities) &&
                Objects.equals(count, otherEntity.count) &&
                Objects.equals(createdAt, otherEntity.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carEntities, count, createdAt);
    }
}
