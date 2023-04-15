package racingcar.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import racingcar.dao.CarsDao;
import racingcar.dao.GameStatesDao;
import racingcar.dao.dto.CarDto;
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
        List<Game> games = new ArrayList<>();
        List<GameStateDto> gameStates = gameStatesDao.selectAll();
        for (GameStateDto gameState : gameStates) {
            List<CarDto> carDtos = carsDao.selectAllBy(gameState.getId());
            Game game = new Game(
                    createCarsWith(carDtos),
                    gameState.getInitialTrialCount(),
                    gameState.getRemainingTrialCount()
            );
            games.add(game);
        }
        return games;
    }

    public void save(Game game) {
        int gameId = gameStatesDao.insert(game.getInitialTrialCount(), game.getRemainingTrialCount());
        for (Car car : game.getCars()) {
            carsDao.insert(gameId, car.getName(), car.getPosition());
        }
    }

    private List<Car> createCarsWith(List<CarDto> carDtos) {
        return carDtos.stream()
                .map(it -> new Car(it.getName(), it.getPosition()))
                .collect(Collectors.toList());
    }
}
