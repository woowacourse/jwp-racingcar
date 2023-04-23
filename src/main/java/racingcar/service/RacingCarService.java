package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.car.CarDao;
import racingcar.dao.game.GameDao;
import racingcar.dao.winner.WinnerDao;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.car.Name;
import racingcar.domain.car.Position;
import racingcar.domain.racinggame.RacingGame;
import racingcar.domain.strategy.move.MoveStrategy;
import racingcar.dto.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {
    private final GameDao gameDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;
    
    public RacingCarService(final GameDao gameDao, final CarDao carDao, final WinnerDao winnerDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
    }
    
    @Transactional
    public GameResponseDto playGame(final GameRequestDto gameRequestDto, final MoveStrategy moveStrategy) {
        final String names = gameRequestDto.getNames();
        final int count = Integer.parseInt(gameRequestDto.getCount());
        final RacingGame racingGame = new RacingGame(names, count);
        
        racingGame.race(moveStrategy);
        
        final Cars cars = racingGame.getCars();
        final long gameId = gameDao.save(new GameDto(racingGame));
        saveCarDao(cars, gameId);
        saveWinnerDao(cars, gameId);
        
        return new GameResponseDto(cars.getWinners(), cars.getCars());
    }
    
    private void saveCarDao(final Cars cars, final long gameId) {
        for (Car car : cars.getCars()) {
            carDao.save(new CarDto(gameId, car));
        }
    }
    
    private void saveWinnerDao(final Cars cars, final long gameId) {
        for (Car car : cars.getWinners()) {
            final Name name = car.getName();
            final long carId = carDao.findIdByGameIdAndName(gameId, name.getName());
            winnerDao.save(new WinnerDto(gameId, carId));
        }
    }
    
    @Transactional(readOnly = true)
    public List<GameResponseDto> findAllGameResult() {
        return findGameResults(gameDao.findAllId());
    }
    
    private List<GameResponseDto> findGameResults(final List<Long> gameIds) {
        final List<GameResponseDto> gameResults = new LinkedList<>();
        for (long gameId : gameIds) {
            final List<WinnerDto> winnerDtos = winnerDao.findWinnerDtosByGameId(gameId);
            final List<Car> winnerCars = getWinnerCars(winnerDtos);
            final List<Car> cars = getCars(gameId);
            
            gameResults.add(new GameResponseDto(winnerCars, cars));
        }
        
        return gameResults;
    }
    
    private List<Car> getWinnerCars(final List<WinnerDto> winnerDtos) {
        return carDao.findCarDtosByCarIds(getCarIds(winnerDtos)).stream()
                .map(carDto -> new Car(new Name(carDto.getName()), new Position(carDto.getPosition())))
                .collect(Collectors.toUnmodifiableList());
    }
    
    private List<Long> getCarIds(final List<WinnerDto> winnerDtos) {
        return winnerDtos.stream()
                .map(WinnerDto::getCarId)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private List<Car> getCars(final long gameId) {
        return carDao.findCarDtosByGameId(gameId).stream()
                .map(carDto -> new Car(new Name(carDto.getName()), new Position(carDto.getPosition())))
                .collect(Collectors.toUnmodifiableList());
    }
}
