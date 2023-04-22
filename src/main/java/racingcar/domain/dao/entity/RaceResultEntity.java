package racingcar.domain.dao.entity;

import java.util.Collections;
import java.util.List;
import org.springframework.lang.Nullable;

public class RaceResultEntity {

    private final Long id;
    private final int trialCount;
    private final String winners;
    private final List<CarEntity> carEntities;

    public RaceResultEntity(@Nullable final Long id, final int trialCount, final String winners,
        final List<CarEntity> carEntities) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.carEntities = carEntities;
    }

    public void addCarEntity(final CarEntity carEntity) {
        carEntities.add(carEntity);
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarEntity> getCarEntities() {
        return Collections.unmodifiableList(carEntities);
    }
}
