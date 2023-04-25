package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import racingcar.Strategy.RandomNumberGenerator;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.model.Cars;
import racingcar.model.RacingGame;
import racingcar.model.Trial;
import racingcar.ui.InputConvertor;

@Service
@Transactional
public class RacingCarService {
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingCarService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public RacingCarResponseDto play(final RacingCarRequestDto request) {
        final Cars cars = Cars.from(InputConvertor.carNames(request));
        final Trial trial = new Trial(InputConvertor.tryCount(request));

        RacingGame racingGame = new RacingGame(cars, trial, new RandomNumberGenerator());
        racingGame.play();

        List<CarEntity> carEntities = insertGame(trial.getTrial(), cars, racingGame.winners());
        return new RacingCarResponseDto(racingGame.winners(), carEntities);
    }

    private List<CarEntity> insertGame(int count, Cars cars, String winners) {
        GameEntity gameEntity = gameDao.insertGame(new GameEntity(winners, count));
        List<CarEntity> carEntities = getCarEntities(cars, gameEntity);
        carEntities.forEach(carDao::insertCar);
        return carEntities;
    }

    private List<CarEntity> getCarEntities(Cars cars, GameEntity gameEntity) {
        return cars.getCars()
            .stream()
            .map(car -> new CarEntity(gameEntity.getId(), car.getName(), car.getPosition()))
            .collect(Collectors.toList());
    }

    public List<RacingCarResponseDto> allGames() {
        List<GameEntity> gameEntities = gameDao.selectAllGames();
        return getRacingResponses(gameEntities);
    }

    private List<RacingCarResponseDto> getRacingResponses(List<GameEntity> gameEntities) {
        List<RacingCarResponseDto> racingCarResponse = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            int gameId = gameEntity.getId();
            List<CarEntity> carEntities = carDao.selectCarsByGameId(gameId);
            racingCarResponse.add(new RacingCarResponseDto(gameEntity.getWinners(), carEntities));
        }
        return racingCarResponse;
    }
}
