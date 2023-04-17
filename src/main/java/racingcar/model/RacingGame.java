package racingcar.model;

import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.manager.CarMoveManager;
import racingcar.model.manager.ThresholdCarMoveManager;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private final CarMoveManager carMoveManager;
    private final Cars cars;
    private final MoveCount moveCount;

    public RacingGame(CarMoveManager carMoveManager, Cars cars, MoveCount moveCount) {
        this.carMoveManager = carMoveManager;
        this.cars = cars;
        this.moveCount = moveCount;
    }

    public void play() {
        for (int i = 0; i < moveCount.getMoveCount(); i++) {
            cars.moveCars(carMoveManager);
        }
    }

    public List<String> getWinners() {
        int maxPosition = cars.getMaxPosition();
        return cars.getCars()
                .stream()
                .filter(car -> car.isSame(maxPosition))
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Car> getResult() {
        return cars.getCars();
    }

}
