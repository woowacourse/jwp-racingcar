package racingcar.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarResultDao;
import racingcar.dao.RacingGameResultDao;
import racingcar.dto.CarResultDto;
import racingcar.dto.RacingGameResultDto;

@Repository
public class WebRacingCarRepository implements RacingCarRepository {

    private final RacingGameResultDao racingGameResultDao;
    private final CarResultDao carResultDao;

    public WebRacingCarRepository(RacingGameResultDao racingGameResultDao, CarResultDao carResultDao) {
        this.racingGameResultDao = racingGameResultDao;
        this.carResultDao = carResultDao;
    }

    @Override
    public void save(RacingGameResultDto racingGameResultDto) {
        int gameId = racingGameResultDao.save(racingGameResultDto.getRound());
        carResultDao.save(gameId, racingGameResultDto.getCarDtos());
    }

    @Override
    public List<CarResultDto> findAll() {
        return carResultDao.findAll();
    }
}
