package racingcar.service;

import java.util.InputMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameResultDao;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.utils.CarsFactory;
import racingcar.utils.RandomPowerGenerator;
import racingcar.utils.RandomPowerMaker;

@Service
public class RacingCarService {

    private final RandomPowerGenerator randomPowerGenerator;
    private final GameResultDao gameResultDao;

    @Autowired
    public RacingCarService(final GameResultDao gameResultDao) {
        this.randomPowerGenerator = new RandomPowerMaker();
        this.gameResultDao = gameResultDao;
    }

    @Transactional
    public GameResultResponseDto startRace(final Cars cars, final TryCount tryCount) {
        moveCars(cars, tryCount);
        GameResultResponseDto gameResult = GameResultResponseDto.toDto(cars);

        gameResultDao.saveGame(tryCount, gameResult);
        return gameResult;
    }

    private void moveCars(final Cars cars, final TryCount tryCount) {
        for (int i = 0; i < tryCount.getTryCount(); i++) {
            cars.moveAll(randomPowerGenerator);
        }
    }
}
