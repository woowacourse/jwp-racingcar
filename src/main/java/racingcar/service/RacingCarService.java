package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;

@Service
public class RacingCarService {

    private final ResultDao resultDao;
    private final RacingCarDao racingCarDao;

    public RacingCarService(ResultDao resultDao, RacingCarDao racingCarDao) {
        this.resultDao = resultDao;
        this.racingCarDao = racingCarDao;
    }

    public void insertGame(int trialCount, Cars cars) {
        long resultId = resultDao.insert(trialCount, cars.getWinnerCars());

        for (Car car : cars.getCars()) {
            racingCarDao.insert(car, resultId);
        }
    }
}
