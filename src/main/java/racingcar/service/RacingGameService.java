package racingcar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameInsertDao;
import racingcar.dao.PlayerInsertDao;
import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.Winner;
import racingcar.controller.ResponseDto;
import racingcar.utils.NumberGenerator;

@Service
public class RacingGameService {

    private static final String DELIMETER = ",";

    private final GameInsertDao gameInsertDao;
    private final PlayerInsertDao playerInsertDao;

    @Autowired
    public RacingGameService(final GameInsertDao gameInsertDao, final PlayerInsertDao playerInsertDao) {
        this.gameInsertDao = gameInsertDao;
        this.playerInsertDao = playerInsertDao;
    }

    public void save(final String winners, final Integer count, final List<Car> cars) {
        int gameId = gameInsertDao.insertGame(winners, count);
        playerInsertDao.insertPlayers(gameId, cars);
    }

    public ResponseDto play(final String names, final Integer count, final NumberGenerator numberGenerator) {
        List<Car> cars = generateCars(names);
        RacingGame racingGame = new RacingGame(cars, count, numberGenerator);
        racingGame.run();
        String winners = getWinners(racingGame);
        return new ResponseDto(winners, cars);
    }

    private List<Car> generateCars(String names) {
        CarGenerator carGenerator = new CarGenerator();
        return carGenerator.generateCars(names.split(DELIMETER));
    }

    private String getWinners(RacingGame racingGame) {
        Winner winner = racingGame.getWinner();
        List<String> winnerNames = winner.getWinnerNames();
        return String.join(DELIMETER, winnerNames);
    }
}
