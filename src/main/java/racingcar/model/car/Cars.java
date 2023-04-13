package racingcar.model.car;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.model.manager.CarMoveManager;
import racingcar.util.RandomNumberGenerator;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void createCars(List<String> names) {
        for (String name : names) {
            cars.add(new Car(new Name(name)));
        }
    }

    public List<Car> getCurrentResult() {
        return List.copyOf(cars);
    }

    public void moveAllCarsOnce(CarMoveManager carMoveManager) {
        cars.forEach(car -> car.move(carMoveManager.isMove(RandomNumberGenerator.getRandomNumber())));
    }

    public List<String> getWinners() {
        int maxPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.isSame(maxPosition))
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private int getMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }
}
