package racingcar.dto;

import java.util.List;

public class RacingGameEntity {

    private final List<CarEntity> carResultEntities;
    private final int round;

    public RacingGameEntity(final List<CarEntity> carResultEntities, final int round) {
        this.carResultEntities = carResultEntities;
        this.round = round;
    }

    public List<CarEntity> getCarResultEntities() {
        return carResultEntities;
    }

    public int getRound() {
        return round;
    }
}
