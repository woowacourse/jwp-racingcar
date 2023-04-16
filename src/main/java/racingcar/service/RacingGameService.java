package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.CarEntity;
import racingcar.dao.GameDao;
import racingcar.dao.GameEntity;
import racingcar.domain.Car.*;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResponseDto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private static final int RACE_START_POINT = 0;

    private final GameDao gameDao;
    private final CarDao carDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(GameDao gameDao, CarDao carDao, NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameResponseDto run(List<String> names, int count) {
        RacingCars racingCars = new RacingCars(CarFactory.generate(names, RACE_START_POINT));
        int tryCount = new TryCount(count).getTries();

        raceCars(racingCars, tryCount);
        LocalTime playTime = LocalTime.now();

        int gameId = saveGame(racingCars, tryCount, playTime);
        saveCars(racingCars, gameId);
        return createResult(racingCars);
    }

    private void raceCars(RacingCars racingCars, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            racingCars.moveCars(numberGenerator);
        }
    }

    private void saveCars(RacingCars racingCars, int gameId) {
        for (Car car : racingCars.getCars()) {
            CarEntity carEntity = new CarEntity(car.getName(), car.getPosition(), gameId);
            carDao.save(carEntity);
        }
    }

    private int saveGame(RacingCars racingCars, int tryCount, LocalTime playTime) {
        List<String> winners = racingCars.pickWinnerCarNames();
        return gameDao.save(winners, tryCount, playTime);
    }

    private RacingGameResponseDto createResult(RacingCars racingCars) {
        String winners = String.join(",", racingCars.pickWinnerCarNames());
        List<CarDto> cars = racingCars.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        return new RacingGameResponseDto(winners, cars);
    }

    public List<RacingGameResponseDto> findAllResults() {
        List<RacingGameResponseDto> results = new ArrayList<>();
        List<GameEntity> games = gameDao.findAll();
        for (GameEntity game : games) {
            List<CarEntity> carEntities = carDao.findByGameId(game.getGameId());
            List<CarDto> cars = carEntities.stream()
                    .map(carEntity -> new CarDto(carEntity.getName(), carEntity.getPosition()))
                    .collect(Collectors.toList());
            results.add(new RacingGameResponseDto(game.getWinners(), cars));
        }
        return results;
    }
}
