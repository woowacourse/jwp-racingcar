package racingcar.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarResultDao;
import racingcar.dao.RacingGameResultDao;
import racingcar.dto.CarEntity;
import racingcar.dto.RacingGameEntity;

@Repository
public class WebRacingCarRepository implements RacingCarRepository {

    private final RacingGameResultDao racingGameResultDao;
    private final CarResultDao carResultDao;

    public WebRacingCarRepository(RacingGameResultDao racingGameResultDao, CarResultDao carResultDao) {
        this.racingGameResultDao = racingGameResultDao;
        this.carResultDao = carResultDao;
    }

    @Override
    public void save(RacingGameEntity racingGameResultDto) {
        int gameId = racingGameResultDao.save(racingGameResultDto.getRound());
        carResultDao.save(gameId, racingGameResultDto.getCarResultEntities());
    }

    @Override
    public List<CarEntity> findAll() {
        return carResultDao.findAll();
    }
}
