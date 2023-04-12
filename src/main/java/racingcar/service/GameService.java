package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.dao.CarsDao;
import racingcar.dao.GamesDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.MoveChance;
import racingcar.domain.RandomMoveChance;

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
        Map<Car, Integer> carIds = carsDao.insert(gameId, game.getCars());
        winnersDao.insert(gameId, findIdsOf(game.findWinners(), carIds));
    }

    private List<Integer> findIdsOf(List<Car> cars, Map<Car, Integer> carIds) {
        return carIds.entrySet().stream()
                .filter(entry -> cars.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
