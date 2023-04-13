package racingcar.repository;

import racingcar.domain.RaceResult;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;

import java.util.List;

public interface CarRaceRepository {

    void save(final RaceResult raceResult);

    List<RaceEntity> findRaceEntities();

    List<CarEntity> findCarEntities(final Long raceResultId);
}
