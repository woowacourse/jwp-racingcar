package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameResultDao;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameHistoriesResponseDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.utils.RandomPowerGenerator;

@Service
public class RacingCarService {

    private final RandomPowerGenerator randomPowerGenerator;
    private final GameResultDao gameResultDao;

    @Autowired
    public RacingCarService(final RandomPowerGenerator powerGenerator ,final GameResultDao gameResultDao) {
        this.randomPowerGenerator = powerGenerator;
        this.gameResultDao = gameResultDao;
    }

    @Transactional(readOnly = true)
    public List<GameHistoriesResponseDto> findGameHistories() {
        return gameResultDao.findGameHistories();
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
