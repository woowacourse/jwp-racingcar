package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarsDao;
import racingcar.dao.GamesDao;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.MoveChance;
import racingcar.dto.CarDto;
import racingcar.service.dto.GameResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GamesDao gamesDao;
    private final CarsDao carsDao;
    private final MoveChance moveChance;

    public GameService(
            final GamesDao gamesDao,
            final CarsDao carsDao,
            final MoveChance moveChance
    ) {
        this.gamesDao = gamesDao;
        this.carsDao = carsDao;
        this.moveChance = moveChance;
    }

    public Game createGameWith(final List<String> names, final int trialCount) {
        return new Game(makeCarsWith(names), trialCount);
    }

    private List<Car> makeCarsWith(final List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public GameResult play(final Game game) {
        while (game.isNotDone()) {
            game.playOnceWith(moveChance);
        }

        return insertResultOf(game);
    }

    private GameResult insertResultOf(final Game game) {
        final int gameId = gamesDao.insert(game.getTrialCount());

        final List<Car> winners = game.findWinners();
        for (Car car : game.getCars()) {
            carsDao.insert(gameId, new CarDto(car.getName(), car.getPosition()), winners.contains(car));
        }

        return getGameResult(game);
    }

    private GameResult getGameResult(final Game game) {
        final List<String> allWinnerNames = game.findWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
        final List<CarDto> allCarInfo = game.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        return new GameResult(allWinnerNames, allCarInfo);
    }

    public GameResult getGameResult(final int gameId) {
        final List<String> allWinnerNames = carsDao.findWinnerNamesByGameId(gameId);
        final List<CarDto> allCarInfo = carsDao.findAllByGameId(gameId);

        return new GameResult(allWinnerNames, allCarInfo);
    }

    public List<Integer> findAllPlayedGameIds() {
        return gamesDao.findAllGameIds();
    }
}
