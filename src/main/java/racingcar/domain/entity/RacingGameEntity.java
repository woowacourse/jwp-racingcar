package racingcar.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

public class RacingGameEntity {

    private Integer id;
    private List<CarEntity> carEntities;
    private Integer count;
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
}
