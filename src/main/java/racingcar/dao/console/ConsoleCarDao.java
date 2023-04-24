package racingcar.dao.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.dao.CarDao;
import racingcar.domain.Car;

public class ConsoleCarDao implements CarDao {
    private final Map<Integer, List<Car>> allCars = new HashMap<>();
    private final Map<Integer, List<Car>> winners = new HashMap<>();

    @Override
    public void insertCars(final List<Car> cars, final int gameId) {
        saveCars(allCars, gameId, cars);
    }

    @Override
    public void updateWinners(final List<Car> cars, final int gameId) {
        validateGameId(gameId);
        saveCars(winners, gameId, cars);
    }

    private void saveCars(final Map<Integer, List<Car>> cars, final int gameId, final List<Car> newCars) {
        if (!cars.containsKey(gameId)) {
            cars.put(gameId, new ArrayList<>());
        }
        List<Car> savedCars = cars.get(gameId);
        savedCars.addAll(newCars);
        cars.put(gameId, savedCars);
    }

    @Override
    public void updatePositions(final List<Car> cars, final int gameId) {
        validateGameId(gameId);
        allCars.put(gameId, cars);
    }

    private void validateGameId(final int gameId) {
        if (!allCars.containsKey(gameId)) {
            throw new IllegalArgumentException("해당하는 게임이 존재하지 않습니다.");
        }
    }

    @Override
    public List<Car> findWinners(final int gameId) {
        return winners.get(gameId);
    }

    @Override
    public List<Car> findCars(final int gameId) {
        return allCars.get(gameId);
    }
}
