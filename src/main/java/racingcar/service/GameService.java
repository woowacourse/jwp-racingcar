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

    public GameService(GamesDao gamesDao, CarsDao carsDao, WinnersDao winnersDao) {
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

    public void play(Game game) {
        while (game.isNotDone()) {
            game.playOnceWith(moveChance);
        }

        insertResultOf(game);
    }

    private void insertResultOf(final Game game) {
        int gameId = gamesDao.insert(game.getTrialCount());
        Map<Car, Integer> carIds = new HashMap<>();
        for (Car car : game.getCars()) {
            int carId = carsDao.insert(gameId, car.getName(), car.getPosition());
            carIds.put(car, carId);
        }
        List<Integer> winnerIds = findIdsOf(game.findWinners(), carIds);
        winnersDao.insert(gameId, winnerIds);
    }

    private List<Integer> findIdsOf(List<Car> cars, Map<Car, Integer> carIds) {
        return carIds.entrySet().stream()
                .filter(entry -> cars.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<Integer> findAllPlayedGameIds() {
        return gamesDao.findAllGameIds();
    }

    public List<String> findWinnersIn(final int gameId) {
        final List<Integer> winnerIds = winnersDao.findAllWinnerIdsByGameId(gameId);
        return winnerIds.stream()
                .map(carsDao::findById)
                .map(CarDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<CarDto> findAllCarsIn(final int gameId) {
        return carsDao.findAllByGameId(gameId);
    }
}
