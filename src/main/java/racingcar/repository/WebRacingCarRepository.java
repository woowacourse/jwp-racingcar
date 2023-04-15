package racingcar.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dto.CarEntity;
import racingcar.dto.RacingGameEntity;

@Repository
public class WebRacingCarRepository implements RacingCarRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public WebRacingCarRepository(RacingGameDao racingGameDao, CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    @Override
    public void save(RacingGameEntity racingGameResultDto) {
        int gameId = racingGameDao.save(racingGameResultDto.getRound());
        carDao.save(gameId, racingGameResultDto.getCarResultEntities());
    }

    @Override
    public List<CarEntity> findAll() {
        return carDao.findAll();
    }
}
