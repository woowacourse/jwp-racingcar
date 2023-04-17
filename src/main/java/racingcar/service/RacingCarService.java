package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.CarResponseDto;
import racingcar.controller.dto.GameResponseDto;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameCount;
import racingcar.domain.PowerGenerator;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.util.CarNamesDivider;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class RacingCarService {

    private final RacingCarDao racingCarDao;

    @Autowired
    public RacingCarService(RacingCarDao racingCarDao) {
        this.racingCarDao = racingCarDao;
    }

    public List<GameResponseDto> findAll() {
        List<GameEntity> racingGameEntities = racingCarDao.findAllGame();
        return racingGameEntities.stream()
                .map(gameEntity -> generateRacingGameResponseDto(gameEntity, findCarEntitiesByGameId(gameEntity.getId())))
                .collect(Collectors.toList());
    }

    private GameResponseDto generateRacingGameResponseDto(GameEntity gameEntity, List<CarEntity> carEntities) {
        return new GameResponseDto(
                gameEntity.getId(),
                gameEntity.getWinners(),
                generateRacingCarResponseDtos(carEntities)
        );
    }

    private List<CarEntity> findCarEntitiesByGameId(int gameId) {
        return racingCarDao.findCarsByGameId(gameId);
    }

    private static List<CarResponseDto> generateRacingCarResponseDtos(List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(RacingCarService::generateRacingCarResponseDto)
                .collect(Collectors.toList());
    }

    private static CarResponseDto generateRacingCarResponseDto(CarEntity carEntity) {
        return new CarResponseDto(carEntity.getId(), carEntity.getName(), carEntity.getPosition());
    }

    public GameResponseDto plays(GameRequestDtoForPlays gameRequestDtoForPlays) {
        Cars cars = generateCars(gameRequestDtoForPlays);
        GameCount gameCount = new GameCount(gameRequestDtoForPlays.getCount());
        progress(cars, gameCount);
        String winners = winnersToString(cars);
        GameEntity gameEntity = generateRacingGameEntity(gameRequestDtoForPlays, winners);
        gameEntity = racingCarDao.saveGame(gameEntity);
        List<CarEntity> carEntities = generateRacingCarEntities(gameEntity.getId(), cars);
        carEntities.forEach(racingCarDao::saveCar);
        return generateRacingGameResponseDto(gameEntity, carEntities);
    }

    private static Cars generateCars(GameRequestDtoForPlays gameRequestDtoForPlays) {
        CarNamesDivider carNamesDivider = new CarNamesDivider();
        List<String> carNamesByDivider = carNamesDivider.divideCarNames(gameRequestDtoForPlays.getNames());
        List<Car> inputCars = carNamesByDivider.stream()
                .map(Car::new)
                .collect(toList());
        return new Cars(inputCars);
    }

    private void progress(Cars cars, GameCount gameCount) {
        while (gameCount.isGameProgress()) {
            gameCount.proceedOnce();
            moveAllCar(cars);
        }
    }

    private void moveAllCar(Cars cars) {
        cars.moveAll(new PowerGenerator(new Random()));
    }

    private String winnersToString(Cars cars) {
        return cars.getWinners().stream()
                .map(Car::getName)
                .collect(joining(","));
    }

    private static GameEntity generateRacingGameEntity(GameRequestDtoForPlays gameRequestDtoForPlays, String winners) {
        return new GameEntity.Builder()
                .count(Integer.parseInt(gameRequestDtoForPlays.getCount()))
                .winners(winners)
                .build();
    }

    private static List<CarEntity> generateRacingCarEntities(int gameId, Cars cars) {
        return cars.getCars()
                .stream()
                .map(car -> generateRacingCarEntity(gameId, car))
                .collect(Collectors.toList());
    }

    private static CarEntity generateRacingCarEntity(int gameId, Car car) {
        return new CarEntity.Builder()
                .name(car.getName())
                .position(car.getPosition())
                .gameId(gameId)
                .build();
    }

}
