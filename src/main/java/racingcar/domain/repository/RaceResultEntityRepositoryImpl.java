package racingcar.domain.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.RaceResultEntity;

@Repository
public class RaceResultEntityRepositoryImpl implements RaceResultEntityRepository {

    private final RaceResultDao raceResultDao;
    private final CarDao carDao;

    public RaceResultEntityRepositoryImpl(final RaceResultDao raceResultDao, final CarDao carDao) {
        this.raceResultDao = raceResultDao;
        this.carDao = carDao;
    }

    @Override
    public void save(final RaceResultEntity raceResultEntity) {
        final Long raceResultId = raceResultDao.save(raceResultEntity.getTrialCount(),
            raceResultEntity.getWinners());
        carDao.saveAll(raceResultId, raceResultEntity.getCarEntities());
    }

    @Override
    public List<RaceResultEntity> findAll() {
        return raceResultDao.findAll();
    }
}
