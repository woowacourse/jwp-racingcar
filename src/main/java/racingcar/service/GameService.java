package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.dao.CarsDao;
import racingcar.dao.GameStatesDao;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.MoveChance;
import racingcar.domain.RandomMoveChance;
import racingcar.repository.GameRepository;
import racingcar.service.dto.CarDto;
import racingcar.service.dto.ResultDto;

@Service
public class GameService {

    private final GameStatesDao gameStatesDao;
    private final CarsDao carsDao;
    private final GameRepository gameRepository;
    private final MoveChance moveChance;

    public GameService(GameStatesDao gameStatesDao, CarsDao carsDao, GameRepository gameRepository) {
        this.gameStatesDao = gameStatesDao;
        this.carsDao = carsDao;
        this.gameRepository = gameRepository;
        this.moveChance = RandomMoveChance.getInstance();
    }

    public ResultDto playWith(List<String> names, int trialCount) {
        Game game = createGameWith(names, trialCount);
        while (game.isNotDone()) {
            game.playOnceWith(moveChance);
        }

        insertResultOf(game);
        return new ResultDto(getNamesOf(game.findWinners()), createDtosOf(game.getCars()));
    }

    public List<ResultDto> getAllResults() {
        List<ResultDto> results = new ArrayList<>();
        List<Game> games = gameRepository.findAll();
        for (Game game : games) {
            ResultDto result = new ResultDto(getNamesOf(game.findWinners()), createDtosOf(game.getCars()));
            results.add(result);
        }
        return results;
    }

    private Game createGameWith(List<String> names, int trialCount) {
        return new Game(makeCarsWith(names), trialCount);
    }

    private List<CarDto> createDtosOf(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private List<Car> makeCarsWith(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private List<String> getNamesOf(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private void insertResultOf(final Game game) {
        int gameId = gameStatesDao.insert(game.getInitialTrialCount(), game.getRemainingTrialCount());
        for (Car car : game.getCars()) {
            carsDao.insert(gameId, car.getName(), car.getPosition());
        }
    }
}
