package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dto.RacingGameResultDto;

@Repository
public class WebRacingCarRepository implements RacingCarRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public WebRacingCarRepository(RacingGameDao racingGameDao, CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    @Override
    public void save(RacingGameResultDto racingGameResultDto) {
        int gameId = racingGameDao.save(racingGameResultDto.getRound());
        carDao.save(gameId, racingGameResultDto.getCarDtos());
    }
}
