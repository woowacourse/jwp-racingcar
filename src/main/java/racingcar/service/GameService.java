package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.GamePlayResponseDto;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.model.Cars;
import racingcar.util.NameFormatConverter;
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

    public void executeRacingGame(Cars cars, int tryCount) {
        for (int count = 0; count < tryCount; count++) {
            cars.moveResult(numberGenerator);
        }

        String winners = NameFormatConverter.joinNameWithDelimiter(cars.getWinners());
        int gameId = gameDao.save(tryCount, winners);
        carDao.saveAll(gameId, cars.getCars());
    }

    public List<GamePlayResponseDto> getGamePlayHistory() {
        return gameDao.selectAll();
    }
}
