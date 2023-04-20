package racingcar.domain;

import java.util.List;

import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.manager.CarMoveManager;
import racingcar.util.ValueEditor;

public class RacingGame {
    public static final String MOVE_COUNT_EXCEPTION_MESSAGE = "1회 이상 이동해야 합니다.";
    public static final int MIN_MOVE_COUNT = 1;
    private final Cars cars;

    private RacingGame(Cars cars) {
        this.cars = cars;
    }

    public static RacingGame initialize(Cars cars) {
        return new RacingGame(cars);
    }

    public void createCars(String inputs) {
        List<String> names = ValueEditor.splitByComma(inputs);
        cars.createCars(names);
    }

    public void moveCars(CarMoveManager carMoveManager, String countInput) {
        int count = ValueEditor.parseStringToInt(countInput);
        validateCount(count);
        for (int i = 0; i < count; i++) {
            cars.moveAllCarsOnce(carMoveManager);
        }
    }

    private void validateCount(int count) {
        if (count < MIN_MOVE_COUNT) {
            throw new IllegalArgumentException(MOVE_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public String getWinner() {
        return ValueEditor.joinWithComma(cars.getWinners());
    }

    public List<Car> getCarMoveResults() {
        return cars.getCurrentResult();
    }
}
