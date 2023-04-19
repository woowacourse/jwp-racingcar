package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.GamePlayResponseDto;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.model.Cars;
import racingcar.model.TryCount;
import racingcar.util.NumberGenerator;

@Service
public class GameService {

    private final NumberGenerator numberGenerator;
    private final GameDao gameDao;
    private final CarDao carDao;

    @Autowired
    public GameService(NumberGenerator numberGenerator, CarDao carDao, GameDao gameDao) {
        this.numberGenerator = numberGenerator;
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public void executeRacingGame(Cars cars, TryCount tryCount) {
        for (int count = 0; count < tryCount.getValue(); count++) {
            cars.moveResult(numberGenerator);
        }
        int gameId = gameDao.save(tryCount.getValue());
        carDao.saveAll(gameId, cars.getCars(), cars.getWinners());
    }

    public List<GamePlayResponseDto> getGamePlayHistory() {
        return carDao.selectAll();
    }
}
