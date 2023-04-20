package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.WinnerDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final GameDao gameDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(final GameDao gameDao, final CarDao carDao, final WinnerDao winnerDao, final NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameResponseDto run(RacingGameRequestDto racingGameRequestDto) {
        RacingGame racingGame = RacingGame.of(racingGameRequestDto.getNamesList(), racingGameRequestDto.getCount());
        racingGame.race(numberGenerator);

        int gameId = gameDao.save(GameEntity.from(racingGame));
        saveCars(racingGame.getCars(), gameId);
        saveWinners(racingGame.pickWinnerCarNames(), gameId);

        return findResultByGameId(gameId);
    }

    private void saveCars(final List<Car> cars, final int gameId) {
        List<CarEntity> carEntities = cars.stream()
                .map(car -> CarEntity.from(car, gameId))
                .collect(Collectors.toCollection(ArrayList::new));
        carDao.saveAll(carEntities);
    }

    private void saveWinners(List<String> winnerNames, int gameId) {
        List<CarEntity> cars = carDao.findCarsByGameID(gameId);
        List<WinnerEntity> winnerEntities = cars.stream()
                .filter(car -> winnerNames.contains(car.getName()))
                .map(car -> new WinnerEntity(car.getCarId(), car.getGameId()))
                .collect(Collectors.toList());

        winnerDao.saveAll(winnerEntities);
    }

    private RacingGameResponseDto findResultByGameId(int gameId) {
        List<CarEntity> carEntities = carDao.findCarsByGameID(gameId);
        List<String> winnerCarNames = findWinnerCarNames(gameId, carEntities);

        List<CarStatusDto> carStatuses = carEntities.stream()
                .map(car -> new CarStatusDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());

        return new RacingGameResponseDto(winnerCarNames, carStatuses);
    }

    private List<String> findWinnerCarNames(final int gameId, final List<CarEntity> cars) {
        List<Integer> winnerCarsId = winnerDao.findWinnerCarIdsByGameId(gameId);
        return cars.stream()
                .filter(car -> winnerCarsId.contains(car.getCarId()))
                .map(CarEntity::getName)
                .collect(Collectors.toList());
    }

    public List<RacingGameResponseDto> findAllGameResult() {
        List<Integer> listOfGameId = gameDao.findGameIds();

        return listOfGameId.stream()
                .map(this::findResultByGameId)
                .collect(Collectors.toList());
    }

}
