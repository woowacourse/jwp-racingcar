package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;

@Service
public class RacingCarService {
    private final PlayResultDao playResultDao;
    private final RacingCarDao racingCarDao;

    public RacingCarService(PlayResultDao playResultDao, RacingCarDao racingCarDao) {
        this.playResultDao = playResultDao;
        this.racingCarDao = racingCarDao;
    }

    public void saveGameResult(Cars cars, TryCount tryCount){
        Long gameId = playResultDao.insertWithKeyHolder(tryCount.getValue(), cars.getWinner());
        for (Car car : cars.getCars()) {
            racingCarDao.insert(gameId, car.getName().getValue(), car.getDistance().getValue());
        }
    }
}
