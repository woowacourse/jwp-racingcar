package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.dto.GamePlayResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarDao carDao;
    private final GameDao gameDao;

    public CarService(final CarDao carDao, final GameDao gameDao) {
        this.carDao = carDao;
        this.gameDao = gameDao;
    }

    public GamePlayResponseDto playGame(final List<String> carNames, final String count) {
        final RacingGame racingGame = RacingGame.of(carNames, count);
        racingGame.playGame();

        return save(count, racingGame);
    }

    public List<GamePlayResponseDto> findGamePlayHistoryAll() {
        return gameDao.findAllWinners().stream().map(gameDto -> new GamePlayResponseDto(gameDto.getWinners(), carDao.findRacingCarByGameId(gameDto.getId()))).collect(Collectors.toList());
    }

    private GamePlayResponseDto save(final String count, final RacingGame racingGame) {
        final String winners = racingGame.findWinnerNames();
        final List<CarEntity> carEntities = mapToCarDto(racingGame);

        final long id = insertGame(count, winners);
        insertCar(carEntities, id);

        return new GamePlayResponseDto(winners, carEntities);
    }

    private List<CarEntity> mapToCarDto(final RacingGame racingGame) {
        final List<Car> cars = racingGame.getCars();

        return cars.stream().map(car -> new CarEntity(car.getName(), car.getPosition())).collect(Collectors.toList());
    }

    private long insertGame(final String count, final String winners) {
        final int trialCount = Integer.parseInt(count);
        return gameDao.save(winners, trialCount);
    }

    private void insertCar(final List<CarEntity> carEntities, final long id) {
        carEntities.forEach((carEntity -> carDao.insert(carEntity.getName(), carEntity.getPosition(), id)));
    }
    
}
