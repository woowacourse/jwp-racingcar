package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameDto;
import racingcar.dto.GameResponse;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.strategy.RandomMovingStrategy;

@Service
public class RacingCarService {

    private static final String DELIMITER = ",";

    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    @Transactional
    public GameResponse play(final GameDto gameDto) {
        RacingGame game = createGame(gameDto.getNames(), gameDto.getCount());
        int gameId = game.getId();
        race(game, gameDto.getCount());

        return makeGameResponse(gameId);
    }

    private RacingGame createGame(final List<String> carNames, final int tryTimes) {
        int gameId = gameDao.insertGame(tryTimes);
        RacingGame game = new RacingGame(gameId, CarFactory.buildCars(carNames), new RandomMovingStrategy());
        race(game, tryTimes);
        saveCars(gameId, game);

        return game;
    }

    private void race(final RacingGame racingGame, final int tryTimes) {
        for (int i = 0; i < tryTimes; i++) {
            racingGame.playSingleRound();
        }
    }

    private void saveCars(final int gameId, final RacingGame game) {
        List<Car> cars = game.getCars();
        convertToEntity(cars).forEach(car -> carDao.insertCar(car, gameId));
    }

    @Transactional
    public List<GameResponse> getGameResults() {
        List<GameResponse> gameResponses = new ArrayList<>();
        List<GameEntity> allGame = gameDao.findAll();

        for (GameEntity gameEntity : allGame) {
            gameResponses.add(
                    makeGameResponse(gameEntity.getId())
            );
        }

        return gameResponses;
    }

    private GameResponse makeGameResponse(final int gameId) {
        String winners = findWinners(gameId);
        List<CarDto> cars = findCars(gameId);

        return GameResponse.of(winners, new ArrayList<>(cars));
    }

    private String findWinners(final int gameId) {
        List<CarDto> carDtos = findCars(gameId);
        Cars cars = new Cars(
                carDtos.stream()
                        .map(carDto -> new Car(carDto.getName(), carDto.getPosition()))
                        .collect(Collectors.toList())
        );

        return String.join(DELIMITER, cars.findWinners());
    }

    private List<CarDto> findCars(final int gameId) {
        List<CarEntity> cars = carDao.findCars(gameId);
        return convertToDto(cars);
    }

    private List<CarEntity> convertToEntity(final List<Car> cars) {
        return cars.stream()
                .map(car -> CarEntity.of(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private List<CarDto> convertToDto(final List<CarEntity> cars) {
        return cars.stream()
                .map(car -> CarDto.of(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
