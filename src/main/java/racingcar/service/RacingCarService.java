package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.RacingGameRequestDtoForPlays;
import racingcar.controller.dto.RacingCarResponseDto;
import racingcar.controller.dto.RacingGameResponseDto;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameCount;
import racingcar.domain.PowerGenerator;
import racingcar.entity.RacingCarEntity;
import racingcar.entity.RacingGameEntity;
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

    public List<RacingGameResponseDto> query() {
        List<RacingGameEntity> racingGameEntities = racingCarDao.findAll(); // List<RacingGameEntity>
        return racingGameEntities.stream()
                .map(RacingCarService::generateRacingGameResponseDto)
                .collect(Collectors.toUnmodifiableList());
    }

    private static RacingGameResponseDto generateRacingGameResponseDto(RacingGameEntity racingGameEntity) {
        return new RacingGameResponseDto(
                racingGameEntity.getId(),
                racingGameEntity.getWinners(),
                generateRacingCarResponseDtos(racingGameEntity));
    }

    private static List<RacingCarResponseDto> generateRacingCarResponseDtos(RacingGameEntity racingGameEntity) {
        return racingGameEntity.getRacingCars()
                .stream()
                .map(RacingCarService::generateRacingCarResponseDto)
                .collect(Collectors.toUnmodifiableList());
    }

    private static RacingCarResponseDto generateRacingCarResponseDto(RacingCarEntity racingCarEntity) {
        return new RacingCarResponseDto(racingCarEntity.getId(), racingCarEntity.getName(), racingCarEntity.getPosition());
    }

    public RacingGameResponseDto plays(RacingGameRequestDtoForPlays racingGameRequestDtoForPlays) {
        Cars cars = generateCars(racingGameRequestDtoForPlays);
        GameCount gameCount = new GameCount(racingGameRequestDtoForPlays.getCount());
        progress(cars, gameCount);
        String winners = winnersToString(cars);
        RacingGameEntity racingGameEntity = generateRacingGameEntity(racingGameRequestDtoForPlays, cars, winners);
        racingGameEntity = racingCarDao.save(racingGameEntity);
        return generateRacingGameResponseDto(racingGameEntity);
    }

    private static RacingGameEntity generateRacingGameEntity(RacingGameRequestDtoForPlays racingGameRequestDtoForPlays, Cars cars, String winners) {
        return new RacingGameEntity.Builder()
                .count(Integer.parseInt(racingGameRequestDtoForPlays.getCount()))
                .winners(winners)
                .racingCars(generateRacingCarEntities(cars))
                .build();
    }

    private static List<RacingCarEntity> generateRacingCarEntities(Cars cars) {
        return cars.getCars()
                .stream()
                .map(RacingCarService::generateRacingCarEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    private static RacingCarEntity generateRacingCarEntity(Car car) {
        return new RacingCarEntity.Builder()
                .name(car.getName())
                .position(car.getPosition())
                .build();
    }

    private static Cars generateCars(RacingGameRequestDtoForPlays racingGameRequestDtoForPlays) {
        CarNamesDivider carNamesDivider = new CarNamesDivider();
        List<String> carNamesByDivider = carNamesDivider.divideCarNames(racingGameRequestDtoForPlays.getNames());
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

}
