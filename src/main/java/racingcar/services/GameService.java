package racingcar.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.WinnerDao;
import racingcar.dto.CarDto;
import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;
import racingcar.model.MoveCount;
import racingcar.model.car.Cars;
import racingcar.model.manager.CarMoveManager;
import racingcar.model.manager.ThresholdCarMoveManager;
import racingcar.util.ValueEditor;

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

    public ResultDto play(GameInfoDto gameInfoDto){
        Cars cars = createCars(gameInfoDto.getNames());
        MoveCount moveCount = MoveCount.from(gameInfoDto.getCount());

        moveCars(cars, moveCount);
        ResultDto resultDto = new ResultDto(getWinner(cars), getResult(cars));
        saveResult(gameInfoDto.getCount(), resultDto);
        return resultDto;
    }

    private Cars createCars(String names) {
        names = ValueEditor.removeSpace(names);
        return Cars.from(ValueEditor.splitByComma(names));
    }

    private void moveCars(Cars cars, MoveCount moveCount) {
        CarMoveManager carMoveManager = new ThresholdCarMoveManager();
        for (int i = 0; i < moveCount.getMoveCount(); i++) {
            cars.moveAllCarsOnce(carMoveManager);
        }
    }

    @Transactional
    public void saveResult(String countInput, ResultDto resultDto) {
        int moveCount = Integer.parseInt(countInput);
        long gameId = gameDao.insertGame(moveCount);
        carDao.insertCar(resultDto, gameId);
        winnerDao.insertWinner(resultDto, gameId);
    }

    private List<CarDto> getResult(Cars cars){
        return cars.getCurrentResult()
                .stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private String getWinner(Cars cars){
        return ValueEditor.joinWithComma(cars.getWinners());
    }
}
