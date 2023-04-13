package racingcar.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.WinnerDao;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.manager.CarMoveManager;
import racingcar.util.CarNameValidator;
import racingcar.util.MoveCountValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private Cars cars;
    private final CarMoveManager carMoveManager;
    private final GameDao gameDao;
    private final WinnerDao winnerDao;
    private final CarDao carDao;

    public GameService(Cars cars, CarMoveManager carMoveManager, GameDao gameDao, WinnerDao winnerDao, CarDao carDao) {
        this.cars = cars;
        this.carMoveManager = carMoveManager;
        this.gameDao = gameDao;
        this.winnerDao = winnerDao;
        this.carDao = carDao;
    }

    public void initialize(){
        this.cars = new Cars(new ArrayList<>());
    }

    public void createCars(String inputs) {
        List<String> names = Arrays.asList(inputs.split(","));
        CarNameValidator.validate(names);
        for (String name : names) {
            Car car = new Car(name);
            cars.add(car);
        }
    }

    public void moveCars(String countInput) {
        MoveCountValidator.validate(countInput);
        int count = Integer.parseInt(countInput);
        for (int i = 0; i < count; i++) {
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

    public List<CarDto> getResult(){
        return cars.getCurrentResult()
                .stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    public String getWinner(){
        return String.join(", ", cars.getWinners());
    }
}
