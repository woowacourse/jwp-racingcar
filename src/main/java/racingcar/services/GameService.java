package racingcar.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import racingcar.dao.GameDao;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.manager.CarMoveManager;
import racingcar.util.CarNameValidator;
import racingcar.util.MoveCountValidator;

@Service
public class GameService {
    private Cars cars;
    private final CarMoveManager carMoveManager;
    private final GameDao gameDao;

    @Autowired
    public GameService(Cars cars, CarMoveManager carMoveManager, GameDao gameDao) {
        this.cars = cars;
        this.carMoveManager = carMoveManager;
        this.gameDao = gameDao;
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

    public void saveResult(String countInput, ResultDto resultDto) {
        int moveCount = Integer.parseInt(countInput);
        gameDao.saveGame(moveCount, resultDto);
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
