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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

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

    public int plays(GameRequestDtoForPlays gameRequestDtoForPlays) {
        RacingGame racingGame = RacingGame.from(gameRequestDtoForPlays);
        racingGame.play();
        int gameId = racingGameDao.saveGame(generateRacingGameEntity(racingGame));
        saveCars(gameId, racingGame.getCars());
        saveWinners(gameId, racingGame.getWinners());
        return gameId;
    }

    private GameEntity generateRacingGameEntity(RacingGame racingGame) {
        return new GameEntity.Builder()
                .count(racingGame.getCount())
                .build();
    }

    private void saveCars(int gameId, Cars cars) {
        cars.getCars()
                .stream()
                .forEach(car -> racingCarDao.saveCar(gameId, car));
    }

    private void saveWinners(int gameId, Winners winners) {
        for (Winner winner : winners.getWinners()) {
            String name = winner.getName();
            int carId = racingCarDao.findIdByName(name);
            winnersDao.saveWinners(gameId, carId);
        }
    }

    private GameResponseDto generateGameResponseDto(int gameId, List<CarEntity> cars, List<Winner> winners) {
        return new GameResponseDto(
                gameId,
                winnersToString(winners),
                generateRacingCarResponseDtos(cars)
        );
    }

    private String winnersToString(List<Winner> winners) {
        return winners.stream()
                .map(Winner::getName)
                .collect(joining(","));
    }

    private List<CarResponseDto> generateRacingCarResponseDtos(List<CarEntity> cars) {
        return cars.stream()
                .map(this::generateRacingCarResponseDto)
                .collect(Collectors.toList());
    }

    private CarResponseDto generateRacingCarResponseDto(CarEntity carEntity) {
        return new CarResponseDto(carEntity.getId(), carEntity.getName(), carEntity.getPosition());
    }

    public List<GameResponseDto> getPreviousGameResults() {
        List<GameEntity> gameEntities = racingGameDao.findAll();
        return generateGameResultsPerGame(gameEntities);
    }

    private List<GameResponseDto> generateGameResultsPerGame(List<GameEntity> gameEntities) {
        List<GameResponseDto> gameResponseDtos = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            List<CarEntity> carsByGameId = racingCarDao.findCarsByGameId(gameEntity.getId());
            List<Winner> winnersByGameId = winnersDao.findWinnerNamesByGameId(gameEntity.getId());
            gameResponseDtos.add(generateGameResponseDto(gameEntity.getId(), carsByGameId, winnersByGameId));
        }
        return gameResponseDtos;
    }

    public GameResponseDto getSavedGameById(int gameId) {
        int racingGameId = racingGameDao.getRacingGameById(gameId).getId();
        List<Winner> winners = winnersDao.findWinnerNamesByGameId(racingGameId);
        List<CarEntity> cars = racingCarDao.findCarsByGameId(gameId);
        return generateGameResponseDto(gameId, cars, winners);
    }

}
