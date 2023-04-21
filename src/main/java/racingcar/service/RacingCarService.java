package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.CarResponseDto;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.*;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.util.CarNamesDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class RacingCarService {

    private final RacingCarDao racingCarDao;
    private final RacingGameDao racingGameDao;
    private final WinnersDao winnersDao;

    @Autowired
    public RacingCarService(RacingCarDao racingCarDao, RacingGameDao racingGameDao, WinnersDao winnersDao) {
        this.racingCarDao = racingCarDao;
        this.racingGameDao = racingGameDao;
        this.winnersDao = winnersDao;
    }

    public List<GameResponseDto> getPreviousGameResults() {
        List<GameEntity> gameEntities = racingGameDao.findAll();
        return generateGameResultsPerGame(gameEntities);
    }

    private List<GameResponseDto> generateGameResultsPerGame(List<GameEntity> gameEntities) {
        List<GameResponseDto> gameResponseDtos = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            List<CarEntity> carsByGameId = racingCarDao.findCarsByGameId(gameEntity.getId());
            String winnersByGameId = winnersDao.findWinnerNamesByGameId(gameEntity.getId());
            gameResponseDtos.add(generateGameResponseDto(gameEntity.getId(), winnersByGameId, carsByGameId));
        }
        return gameResponseDtos;
    }

    private GameResponseDto generateGameResponseDto(int gameId, String winnersByGameId, List<CarEntity> carsByGameId) {
        return new GameResponseDto(
                gameId,
                winnersByGameId,
                generateRacingCarResponseDtos(carsByGameId)
        );
    }

    private List<CarResponseDto> generateRacingCarResponseDtos(List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(this::generateRacingCarResponseDto)
                .collect(Collectors.toList());
    }

    public CarResponseDto generateRacingCarResponseDto(CarEntity carEntity) {
        return new CarResponseDto(carEntity.getId(), carEntity.getName(), carEntity.getPosition());
    }

//    private List<CarResponseDto> generateRacingCarResponseDtos(GameEntity gameEntity) {
//        return gameEntity.getRacingCars()
//                .stream()
//                .map(this::generateRacingCarResponseDto)
//                .collect(Collectors.toList());
//    }

//    public GameResponseDto generateGameResponseDto(GameEntity gameEntity) {
//        return new GameResponseDto(
//                gameEntity.getId(),
//                gameEntity.getWinners(),
//                generateRacingCarResponseDtos(gameEntity)
//        );
//    }

    public GameResponseDto plays(GameRequestDtoForPlays gameRequestDtoForPlays) {
        Cars cars = generateCars(gameRequestDtoForPlays);
        RacingGame racingGame = new RacingGame(cars, new GameCount(gameRequestDtoForPlays.getCount()));
        racingGame.play();
        String winners = winnersToString(racingGame.getWinners());

        GameEntity gameResultEntity = generateRacingGameEntity(gameRequestDtoForPlays, cars, winners);
        int gameId = racingGameDao.saveGame(gameResultEntity);
        // TODO : cars를 dto로 변환
        List<CarEntity> carEntities = new ArrayList<>();
        for (Car car : cars.getCars()) {
            int carId = racingCarDao.saveCar(gameId, car);
            winnersDao.saveWinners(gameId, carId);
            carEntities.add(new CarEntity.Builder()
                    .name(car.getName())
                    .position(car.getPosition())
                    .racingGameId(gameId)
                    .build());
        }
        return generateGameResponseDto(gameId, winners, carEntities);
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
