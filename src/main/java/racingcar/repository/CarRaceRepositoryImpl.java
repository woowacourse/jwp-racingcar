package racingcar.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RaceResult;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CarRaceRepositoryImpl implements CarRaceRepository {

    private final CarDao carDao;
    private final RaceResultDao raceResultDao;

    public CarRaceRepositoryImpl(CarDao carDao, RaceResultDao raceResultDao) {
        this.carDao = carDao;
        this.raceResultDao = raceResultDao;
    }

    @Override
    @Transactional
    public void save(final RaceResult raceResult) {
        final Long savedResultId = raceResultDao.save(raceResult.getTrialCount(),
                raceResult.getWinners());
        carDao.saveAll(savedResultId, raceResult.getCars());
    }

    @Override
    public List<RaceEntity> findRaceEntities() {
        return raceResultDao.findAll();
    }

    @Override
    public List<CarEntity> findCarEntities(final Long raceResultId) {
        return carDao.findAll(raceResultId);
    }
}
