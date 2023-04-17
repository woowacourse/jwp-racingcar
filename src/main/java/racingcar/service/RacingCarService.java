package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.domain.car.Car;
import racingcar.domain.car.CarFactory;
import racingcar.domain.car.Cars;
import racingcar.domain.game.GameRecorder;
import racingcar.domain.game.GameResultOfCar;
import racingcar.domain.game.GameSystem;
import racingcar.domain.game.RandomSingleDigitGenerator;
import racingcar.dto.CarDTO;
import racingcar.dto.ResultDTO;

@Service
public class RacingCarService {
    private static final int INITIAL_GAME_ID = 1;

    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public ResultDTO play(final List<String> names, final int count) {
        final GameSystem gameSystem = new GameSystem(count, new GameRecorder(new ArrayList<>()));
        final Long gameId = gameDao.insert(GameEntity.create(count));
        final Cars cars = makeCars(names);

        gameSystem.executeRace(cars, new RandomSingleDigitGenerator());
        insertCar(cars, gameId, gameSystem);

        return createGameDTO(count, gameSystem);
    }

    private Cars makeCars(final List<String> names) {
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(names);
    }

    private void insertCar(final Cars cars, final Long id, final GameSystem gameSystem) {
        final List<CarEntity> carEntities = new ArrayList<>();
        for (Car car : cars.getCars()) {
            carEntities.add(CarEntity.create(car.getName(), car.getPosition(), id, isWin(car, gameSystem)));
        }
        carDao.batchInsert(carEntities);
    }

    private boolean isWin(final Car car, final GameSystem gameSystem) {
        List<String> winners = getWinners(gameSystem);
        return winners.contains(car.getName());
    }

    private List<String> getWinners(final GameSystem gameSystem) {
        List<GameResultOfCar> winnersGameResult = gameSystem.getWinnersGameResult();
        return winnersGameResult.stream()
                .map(gameResultOfCar -> gameResultOfCar.getCarName())
                .collect(Collectors.toList());
    }

    private ResultDTO createGameDTO(final int count, final GameSystem gameSystem) {
        final List<String> winners = getWinners(gameSystem);
        final List<CarDTO> carDTOs = getCarDTOs(count, gameSystem);
        return new ResultDTO(winners, carDTOs);
    }

    private List<CarDTO> getCarDTOs(final int count, final GameSystem gameSystem) {
        List<GameResultOfCar> allGameResult = gameSystem.getAllGameResult();
        return allGameResult.stream()
                .filter(gameResultOfCar -> gameResultOfCar.isSameGameRound(count))
                .map(gameResultOfCar -> new CarDTO(gameResultOfCar.getCarName(), gameResultOfCar.getPosition()))
                .collect(Collectors.toList());
    }

    public List<ResultDTO> getSavedGames() {
        List<ResultDTO> playingCars = new ArrayList<>();

        final int games = gameDao.countGames();
        for (int gameId = INITIAL_GAME_ID; gameId <= games; gameId++) {
            final List<CarDTO> carDTO = carDao.selectAll(gameId);
            final List<String> winnerNames = carDao.selectWinners(gameId);

            playingCars.add(new ResultDTO(winnerNames, carDTO));
        }

        return new ArrayList<>(playingCars);
    }
}
