package racingcar.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.WinnerDao;
import racingcar.dto.CarDto;
import racingcar.dto.GameInformationDto;
import racingcar.dto.GameResultDto;
import racingcar.model.MoveCount;
import racingcar.model.RacingGame;
import racingcar.model.car.Cars;
import racingcar.model.manager.ThresholdCarMoveManager;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameDao gameDao;
    private final WinnerDao winnerDao;
    private final CarDao carDao;

    public GameService(GameDao gameDao, WinnerDao winnerDao, CarDao carDao) {
        this.gameDao = gameDao;
        this.winnerDao = winnerDao;
        this.carDao = carDao;
    }

    @Transactional
    public GameResultDto play(GameInformationDto gameInformationDto){
        Cars cars = Cars.from(gameInformationDto.getNames());
        MoveCount moveCount = MoveCount.from(gameInformationDto.getCount());
        RacingGame racingGame = new RacingGame(new ThresholdCarMoveManager(), cars, moveCount);
        racingGame.play();

        GameResultDto gameResultDto = new GameResultDto(racingGame.getWinners(), createCarDto(racingGame));
        saveResult(moveCount, gameResultDto);
        return gameResultDto;
    }

    private void saveResult(MoveCount moveCount, GameResultDto gameResultDto) {
        long gameId = gameDao.insertGame(moveCount);
        carDao.insertCar(gameResultDto, gameId);
        winnerDao.insertWinner(gameResultDto, gameId);
    }

    private List<CarDto> createCarDto(RacingGame racingGame){
        return racingGame.getResult()
                .stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
