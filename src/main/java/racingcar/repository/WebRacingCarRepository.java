package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.EntityMapper;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.domain.RacingGame;

import java.util.List;

@Repository
public class WebRacingCarRepository implements RacingCarRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public WebRacingCarRepository(RacingGameDao racingGameDao, CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    @Override
    public void saveRacingGame(RacingGame racingGame) {
        RacingGameEntity racingGameEntity = EntityMapper.toRacingGameEntity(racingGame);
        int gameId = racingGameDao.save(racingGameEntity);

        List<CarEntity> carEntities = EntityMapper.toCarEntities(gameId, racingGame);
        carDao.saveAll(carEntities);
    }
}
