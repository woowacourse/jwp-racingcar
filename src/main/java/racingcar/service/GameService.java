package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.GamePlayResponseDto;
import racingcar.dao.car.CarDao;
import racingcar.dao.game.GameDao;
import racingcar.dao.dto.GameFinishedCarDto;
import racingcar.model.Cars;
import racingcar.model.RacingCarGame;
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
        final RacingCarGame racingCarGame = new RacingCarGame(cars, tryCount, numberGenerator);
        racingCarGame.execute();

        int gameId = gameDao.save(tryCount.getValue());
        carDao.saveAll(gameId, cars.getCars(), cars.getWinners());
    }

    public List<GamePlayResponseDto> getGamePlayHistory() {
        final Map<Integer, List<GameFinishedCarDto>> carsByGame = carDao.selectAll();

        List<GamePlayResponseDto> gameHistory = new ArrayList<>();
        for (Integer gameId : carsByGame.keySet()) {
            final List<GameFinishedCarDto> cars = carsByGame.get(gameId);
            final GamePlayResponseDto gameResult = new GamePlayResponseDto(cars);
            gameHistory.add(gameResult);
        }
        return gameHistory;
    }
}
