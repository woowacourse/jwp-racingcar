package racingcar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameInsertDao;
import racingcar.dao.PlayerInsertDao;
import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.utils.NumberGenerator;

@Service
public class RacingGameService {

    private static final String DELIMITER = ",";

    private final GameInsertDao gameInsertDao;
    private final PlayerInsertDao playerInsertDao;

    @Autowired
    public RacingGameService(final GameInsertDao gameInsertDao, final PlayerInsertDao playerInsertDao) {
        this.gameInsertDao = gameInsertDao;
        this.playerInsertDao = playerInsertDao;
    }

    public void save(final RacingGame racingGame) {
        int gameId = gameInsertDao.insertGame(racingGame.getTryCount());
        playerInsertDao.insertPlayers(gameId, racingGame.getCars(), racingGame.getWinner().getWinnerNames());
    }

    public RacingGame play(final String names, final Integer count, final NumberGenerator numberGenerator) {
        List<Car> cars = generateCars(names);
        RacingGame racingGame = new RacingGame(cars, count, numberGenerator);
        racingGame.run();
        return racingGame;
    }

    private List<Car> generateCars(String names) {
        CarGenerator carGenerator = new CarGenerator();
        return carGenerator.generateCars(names.split(DELIMITER));
    }
}
