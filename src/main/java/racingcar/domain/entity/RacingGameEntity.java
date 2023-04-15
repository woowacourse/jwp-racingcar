package racingcar.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

public class RacingGameEntity {

    private int id;
    private List<CarEntity> carEntities;
    private int count;
    private LocalDateTime createdAt;

    public RacingGameEntity(final List<CarEntity> carEntities, final int count) {
        this.carEntities = carEntities;
        this.count = count;
    }

    public RacingGameEntity(final int id, final int count, final LocalDateTime createdAt) {
        this.id = id;
        this.count = count;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public List<CarEntity> getCarEntities() {
        return carEntities;
    }

    public void setCarEntities(final List<CarEntity> carEntities) {
        this.carEntities = carEntities;
    }

    public int getCount() {
        return count;
    }


}
