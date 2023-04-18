package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarsDao;
import racingcar.dao.GamesDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.MoveChance;
import racingcar.domain.RandomMoveChance;
import racingcar.dto.CarDto;
import racingcar.service.dto.SingleGameResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GamesDao gamesDao;
    private final CarsDao carsDao;
    private final WinnersDao winnersDao;
    private final MoveChance moveChance;

    public GameService(
            final GamesDao gamesDao,
            final CarsDao carsDao,
            final WinnersDao winnersDao
    ) {
        this.gamesDao = gamesDao;
        this.carsDao = carsDao;
        this.winnersDao = winnersDao;
        this.moveChance = RandomMoveChance.getInstance();
    }

    public Game createGameWith(List<String> names, int trialCount) {
        return new Game(makeCarsWith(names), trialCount);
    }

    private List<Car> makeCarsWith(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public SingleGameResult play(Game game) {
        while (game.isNotDone()) {
            game.playOnceWith(moveChance);
        }

        return insertResultOf(game);
    }

    private SingleGameResult insertResultOf(final Game game) {
        final int gameId = gamesDao.insert(game.getTrialCount());

        final Map<Car, Integer> carIds = new HashMap<>();
        for (Car car : game.getCars()) {
            final int carId = carsDao.insert(gameId, car.getName(), car.getPosition());
            carIds.put(car, carId);
        }

        final List<Integer> winnerIds = findIdsOf(game.findWinners(), carIds);
        winnersDao.insert(gameId, winnerIds);

        return getGameResult(game);
    }

    private List<Integer> findIdsOf(List<Car> cars, Map<Car, Integer> carIds) {
        return carIds.entrySet().stream()
                .filter(entry -> cars.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private SingleGameResult getGameResult(final Game game) {
        final List<String> allWinnerNames = game.findWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
        final List<CarDto> allCarInfo = game.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        return new SingleGameResult(allWinnerNames, allCarInfo);
    }

    public SingleGameResult getGameResult(final int gameId) {
        final List<String> allWinnerNames = winnersDao.findAllWinnerNameByGameId(gameId);
        final List<CarDto> allCarInfo = carsDao.findAllByGameId(gameId);

        return new SingleGameResult(allWinnerNames, allCarInfo);
    }

    public List<Integer> findAllPlayedGameIds() {
        return gamesDao.findAllGameIds();
    }
}
