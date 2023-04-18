package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.CarResponseDto;
import racingcar.controller.dto.GameRequestDtoForPlays;
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
        List<GameEntity> racingGameEntities = racingCarDao.findAll(); // List<RacingGameEntity>
        return racingGameEntities.stream()
                .map(this::generateRacingGameResponseDto)
                .collect(Collectors.toList());
    }

    public GameResponseDto generateRacingGameResponseDto(GameEntity gameEntity) {
        return new GameResponseDto(
                gameEntity.getId(),
                gameEntity.getWinners(),
                generateRacingCarResponseDtos(gameEntity)
        );
    }

    private List<CarResponseDto> generateRacingCarResponseDtos(GameEntity gameEntity) {
        return gameEntity.getRacingCars()
                .stream()
                .map(this::generateRacingCarResponseDto)
                .collect(Collectors.toList());
    }

    public CarResponseDto generateRacingCarResponseDto(CarEntity carEntity) {
        return new CarResponseDto(carEntity.getId(), carEntity.getName(), carEntity.getPosition());
    }

    public GameResponseDto plays(GameRequestDtoForPlays gameRequestDtoForPlays) {
        GameEntity gameResultEntity = getGameResultEntity(gameRequestDtoForPlays);
        gameResultEntity = racingCarDao.saveGame(gameResultEntity);
        return generateRacingGameResponseDto(gameResultEntity);
    }

    public GameEntity getGameResultEntity(GameRequestDtoForPlays gameRequestDtoForPlays) {
        Cars cars = generateCars(gameRequestDtoForPlays);
        GameCount gameCount = new GameCount(gameRequestDtoForPlays.getCount());
        progress(cars, gameCount);
        String winners = winnersToString(cars);
        return generateRacingGameEntity(gameRequestDtoForPlays, cars, winners);
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

    private GameEntity generateRacingGameEntity(GameRequestDtoForPlays gameRequestDtoForPlays, Cars cars, String winners) {
        return new GameEntity.Builder()
                .count(Integer.parseInt(gameRequestDtoForPlays.getCount()))
                .winners(winners)
                .racingCars(generateRacingCarEntities(cars))
                .build();
    }

    private List<CarEntity> generateRacingCarEntities(Cars cars) {
        return cars.getCars()
                .stream()
                .map(this::generateRacingCarEntity)
                .collect(Collectors.toList());
    }

    private CarEntity generateRacingCarEntity(Car car) {
        return new CarEntity.Builder()
                .name(car.getName())
                .position(car.getPosition())
                .build();
    }

}
