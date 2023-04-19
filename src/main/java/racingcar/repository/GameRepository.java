package racingcar.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import racingcar.dao.CarsDao;
import racingcar.dao.GameStatesDao;
import racingcar.dao.dto.GameStateDto;
import racingcar.domain.Car;
import racingcar.domain.Game;

@Repository
public class GameRepository {

    private final GameStatesDao gameStatesDao;
    private final CarsDao carsDao;

    public GameRepository(GameStatesDao gameStatesDao, CarsDao carsDao) {
        this.gameStatesDao = gameStatesDao;
        this.carsDao = carsDao;
    }

    public List<Game> findAll() {
        return gameStatesDao.selectAll().stream()
                .map(this::createGameWith)
                .collect(Collectors.toList());
    }

    public void save(Game game) {
        int gameId = gameStatesDao.insert(game.getInitialTrialCount(), game.getRemainingTrialCount());
        for (Car car : game.getCars()) {
            carsDao.insert(gameId, car.getName(), car.getPosition());
        }
    }

    private Game createGameWith(GameStateDto gameState) {
        return new Game(
                findCarsBy(gameState.getId()),
                gameState.getInitialTrialCount(),
                gameState.getRemainingTrialCount()
        );
    }

    private List<Car> findCarsBy(Integer gameId) {
        return carsDao.selectAllBy(gameId)
                .stream()
                .map(it -> new Car(it.getName(), it.getPosition()))
                .collect(Collectors.toList());
    }
}
