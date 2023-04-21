package racingcar.domain.car;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.manager.CarMoveManager;
import racingcar.util.RandomNumberGenerator;

public class Cars {
    public static final int MIN_CAR_NUMBER = 2;
    private static final String EXCEPTION_CAR_NUMBER = "2개 이상의 자동차를 입력해 주세요.";
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void createCars(List<String> names) {
        validate(names);
        for (String name : names) {
            cars.add(new Car(new Name(name)));
        }
    }

    private void validate(List<String> names) {
        int size = names.size();
        if (size < MIN_CAR_NUMBER) {
            throw new IllegalArgumentException(EXCEPTION_CAR_NUMBER);
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
