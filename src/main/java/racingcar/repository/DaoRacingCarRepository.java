package racingcar.repository;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.entity.CarEntity;
import racingcar.domain.entity.RacingGameEntity;

@Repository
public class DaoRacingCarRepository implements RacingCarRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public DaoRacingCarRepository(RacingGameDao racingGameDao, CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    @Override
    public void save(RacingGameEntity racingGameEntity) {
        int gameId = racingGameDao.save(racingGameEntity.getCount());
        carDao.saveAll(gameId, racingGameEntity.getCarEntities());
    }

    @Override
    public List<RacingGameEntity> findAll() {
        List<RacingGameEntity> racingGameEntities = racingGameDao.findAll();
        List<CarEntity> carEntities = carDao.findAll();

        for (RacingGameEntity racingGameEntity : racingGameEntities) {
            List<CarEntity> carEntitiesByGameId = carEntities.stream()
                    .filter(carEntity -> carEntity.getGameId() == racingGameEntity.getId())
                    .collect(toList());
            racingGameEntity.setCarEntities(carEntitiesByGameId);
        }

        return racingGameEntities;
    }
}
