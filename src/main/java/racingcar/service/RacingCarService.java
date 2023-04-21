package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.CarResponseDto;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.dao.RacingCarDao;
import racingcar.domain.*;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.util.CarNamesDivider;

import java.util.List;
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

    public List<GameResponseDto> getPreviousWinners() {
        List<GameEntity> racingGameEntities = racingCarDao.findAll();
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
        RacingGame racingGame = new RacingGame(cars, new GameCount(gameRequestDtoForPlays.getCount()));
        racingGame.play();
        String winners = winnersToString(racingGame.getWinners());
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

    private String winnersToString(Winners winners) {
        return winners.getWinners().stream()
                .map(Winner::getName)
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
