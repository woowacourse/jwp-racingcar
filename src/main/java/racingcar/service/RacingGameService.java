package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.GameResponseDto;
import racingcar.dao.GameInsertDao;
import racingcar.dao.GameQueryDao;
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
    private final GameQueryDao gameQueryDao;

    @Autowired
    public RacingGameService(
            final GameInsertDao gameInsertDao,
            final PlayerInsertDao playerInsertDao,
            final GameQueryDao gameQueryDao
    ) {
        this.gameInsertDao = gameInsertDao;
        this.playerInsertDao = playerInsertDao;
        this.gameQueryDao = gameQueryDao;
    }

    public RacingGame playAndSave(final String names, final Integer count, final NumberGenerator numberGenerator) {
        RacingGame racingGame = play(names, count, numberGenerator);
        save(racingGame);
        return racingGame;
    }

    private void save(final RacingGame racingGame) {
        int gameId = gameInsertDao.insertGame(racingGame.getTryCount());
        playerInsertDao.insertPlayers(gameId, racingGame.getCars(), racingGame.getWinner().getWinnerNames());
    }

    private RacingGame play(final String names, final Integer count, final NumberGenerator numberGenerator) {
        List<Car> cars = generateCars(names);
        RacingGame racingGame = new RacingGame(cars, count, numberGenerator);
        racingGame.run();
        return racingGame;
    }

    private List<Car> generateCars(String names) {
        CarGenerator carGenerator = new CarGenerator();
        return carGenerator.generateCars(names.split(DELIMITER));
    }

    public List<GameResponseDto> getGameResponseDtos() {
        List<Integer> gameIds = gameQueryDao.getGameIds();
        return gameIds.stream()
                .map(this::getGameResponseDto)
                .collect(Collectors.toList());
    }

    private GameResponseDto getGameResponseDto(final int id) {
        String winners = String.join(DELIMITER, gameQueryDao.getWinners(id));
        List<Car> cars = gameQueryDao.getCars(id);
        return new GameResponseDto(winners, cars);
    }
}
