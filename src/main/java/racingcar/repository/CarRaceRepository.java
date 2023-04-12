package racingcar.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RaceResult;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;

@Repository
@Transactional(readOnly = true)
public class CarRaceRepository {

    private final CarDao carDao;
    private final RaceResultDao raceResultDao;

    public CarRaceRepository(CarDao carDao, RaceResultDao raceResultDao) {
        this.carDao = carDao;
        this.raceResultDao = raceResultDao;
    }

    @Transactional
    public void save(final RaceResult raceResult) {
        final Long savedResultId = raceResultDao.save(raceResult.getTrialCount(),
            raceResult.getWinners());
        carDao.saveAll(savedResultId, raceResult.getCars());
    }

    public List<RaceEntity> findRaceEntities() {
        return raceResultDao.findAll();
    }

    public List<CarEntity> findCarEntities(final Long raceResultId) {
        return carDao.findAll(raceResultId);
    }
}
