package racingcar.repository;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.entity.CarResultEntity;
import racingcar.domain.entity.RacingGameResultEntity;

@Repository
public class DaoRacingCarRepository implements RacingCarRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public DaoRacingCarRepository(RacingGameDao racingGameDao, CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    @Override
    public void save(RacingGameResultEntity racingGameResultEntity) {
        int gameId = racingGameDao.save(racingGameResultEntity.getCount());
        carDao.saveAll(gameId, racingGameResultEntity.getCarEntities());
    }

    @Override
    public List<RacingGameResultEntity> findAll() {
        List<RacingGameResultEntity> racingGameEntities = racingGameDao.findAll();
        List<CarResultEntity> carEntities = carDao.findAll();

        for (RacingGameResultEntity racingGameResultEntity : racingGameEntities) {
            List<CarResultEntity> carEntitiesByGameId = carEntities.stream()
                    .filter(carEntity -> carEntity.getGameId() == racingGameResultEntity.getId())
                    .collect(toList());
            racingGameResultEntity.setCarEntities(carEntitiesByGameId);
        }

        return racingGameEntities;
    }
}
