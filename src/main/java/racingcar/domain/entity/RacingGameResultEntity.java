package racingcar.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class RacingGameResultEntity {

    private Integer id;
    private List<CarResultEntity> carEntities;
    private final Integer count;
    private LocalDateTime createdAt;

    public RacingGameResultEntity(List<CarResultEntity> carEntities, Integer count) {
        this(null, carEntities, count, null);
    }

    public RacingGameResultEntity(Integer id, Integer count, LocalDateTime createdAt) {
        this(id, null, count, createdAt);
    }

    public RacingGameResultEntity(Integer id, List<CarResultEntity> carEntities, Integer count,
                                  LocalDateTime createdAt) {
        this.id = id;
        this.carEntities = carEntities;
        this.count = count;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public List<CarResultEntity> getCarEntities() {
        return carEntities;
    }

    public void setCarEntities(List<CarResultEntity> carEntities) {
        this.carEntities = carEntities;
    }

    public Integer getCount() {
        return count;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        RacingGameResultEntity otherEntity = (RacingGameResultEntity) other;

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
