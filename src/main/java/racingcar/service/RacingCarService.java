package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.Strategy.RandomNumberGenerator;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dto.RacingCarResponseDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.model.Cars;
import racingcar.model.RacingGame;
import racingcar.model.Trial;

@Service
public class RacingCarService {
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public RacingCarResponseDto play(final Cars cars, final Trial trial) {
        RacingGame racingGame = new RacingGame(cars, trial, new RandomNumberGenerator());
        racingGame.play();
        List<CarEntity> carEntities = insertResult(trial.getTrial(), cars, racingGame.winners());
        return new RacingCarResponseDto(racingGame.winners(), carEntities);
    }

    private List<CarEntity> insertResult(int count, Cars cars, String winners) {
        GameEntity gameEntity = gameDao.insertRacingResult(new GameEntity(winners, count));
        List<CarEntity> carEntities = getCars(cars, gameEntity);
        carEntities.forEach(carDao::insertCar);
        return carEntities;
    }

    private List<CarEntity> getCars(Cars cars, GameEntity gameEntity) {
        return cars.getCars()
            .stream()
            .map(car -> new CarEntity(gameEntity.getId(), car.getName(), car.getPosition()))
            .collect(Collectors.toList());
    }

    public List<RacingCarResponseDto> allGames() {
        List<GameEntity> gameEntities = gameDao.selectAllResults();
        return getRacingResponses(gameEntities);
    }

    private List<RacingCarResponseDto> getRacingResponses(List<GameEntity> gameEntities) {
        List<RacingCarResponseDto> racingCarResponse = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            int playResultId = gameEntity.getId();
            List<CarEntity> carEntities = carDao.selectCarsByGameId(playResultId);
            racingCarResponse.add(new RacingCarResponseDto(gameEntity.getWinners(), carEntities));
        }
        return racingCarResponse;
    }
}
