package racingcar.service;

import racingcar.dao.car.CarDao;
import racingcar.dao.game.GameDao;
import racingcar.dao.winner.WinnerDao;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.racinggame.RacingGame;
import racingcar.domain.strategy.move.MoveStrategy;
import racingcar.dto.*;

import java.util.LinkedList;
import java.util.List;

public class ConsoleRacingCarService implements RacingCarService {
    private final GameDao gameDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;
    
    public ConsoleRacingCarService(final GameDao gameDao, final CarDao carDao, final WinnerDao winnerDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
    }
    
    @Override
    public void playGame(final GameInputDto gameInputDto, final MoveStrategy moveStrategy) {
        final RacingGame racingGame = race(gameInputDto, moveStrategy);
        
        final Long gameId = insertGameDao(racingGame);
        final Cars cars = racingGame.getCars();
        
        insertCarDao(gameId, cars);
        insertWinnerDao(gameId, cars);
    }
    
    private RacingGame race(final GameInputDto gameInputDto, final MoveStrategy moveStrategy) {
        final RacingGame racingGame = new RacingGame(gameInputDto.getCarNames(), gameInputDto.getTryNumber());
        racingGame.race(moveStrategy);
        return racingGame;
    }
    
    private Long insertGameDao(final RacingGame racingGame) {
        final GameDto gameDto = new GameDto(racingGame);
        return gameDao.insert(gameDto);
    }
    
    private void insertCarDao(final Long gameId, final Cars cars) {
        for (Car car : cars.getCars()) {
            final CarDto carDto = new CarDto(gameId, car);
            carDao.insert(carDto);
        }
    }
    
    private void insertWinnerDao(final Long gameId, final Cars cars) {
        for (Car car : cars.getWinners()) {
            final CarDto carDto = new CarDto(gameId, car);
            final long carId = carDao.findIdByCarDto(carDto);
            
            winnerDao.insert(new WinnerDto(gameId, carId));
        }
    }
    
    @Override
    public List<GameResultDto> findAllGameResult() {
        return findGameResults(gameDao.findAllId());
    }
    
    private List<GameResultDto> findGameResults(final List<Long> gameIds) {
        final List<GameResultDto> gameResults = new LinkedList<>();
        for (long gameId : gameIds) {
            final GameDto gameDto = gameDao.findById(gameId);
            final List<WinnerDto> winnerDtos = winnerDao.findWinnerDtosByGameId(gameId);
            final List<CarDto> winnerCarDtos = carDao.findCarDtosByWinnerDtos(winnerDtos);
            final List<CarDto> carDtos = carDao.findCarDtosByGameId(gameId);
            
            gameResults.add(new GameResultDto(gameDto, winnerCarDtos, carDtos));
        }
        
        return gameResults;
    }
}
