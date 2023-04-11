package racingcar.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import racingcar.dto.CarDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.manager.CarMoveManager;
import racingcar.util.CarNameValidator;
import racingcar.util.MoveCountValidator;

@Service
public class GameService {
    @Autowired
    private Cars cars;
    @Autowired
    private final CarMoveManager carMoveManager;

    public GameService(Cars cars, CarMoveManager carMoveManager) {
        this.cars = cars;
        this.carMoveManager = carMoveManager;
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
