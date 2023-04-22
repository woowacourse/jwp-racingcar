package racingcar.domain.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceResultEntity;

public class RaceResultCarJoinDto {

    private final Long raceResultId;
    private final int trialCount;
    private final String winners;
    private final Long carId;
    private final String carName;
    private final int carPosition;

    public RaceResultCarJoinDto(final Long raceResultId, final int trialCount, final String winners,
        final Long carId,
        final String carName, final int carPosition) {
        this.raceResultId = raceResultId;
        this.trialCount = trialCount;
        this.winners = winners;
        this.carId = carId;
        this.carName = carName;
        this.carPosition = carPosition;
    }

    public CarEntity makeCarEntity() {
        return new CarEntity(carId, carName, carPosition);
    }

    public RaceResultEntity makeRaceResultEntity() {
        final List<CarEntity> carEntities = new ArrayList<>();
        carEntities.add(makeCarEntity());
        return new RaceResultEntity(raceResultId, trialCount, winners, carEntities);
    }

    public Long getRaceResultId() {
        return raceResultId;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public Long getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public int getCarPosition() {
        return carPosition;
    }
}
