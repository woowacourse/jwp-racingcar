package racingcar.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cars {
    private static final int RANDOM_NUM_MAX_VALUE = 10;
    private static final Random random = new Random();

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(List<String> carNames) {
        return carNames.stream()
                .map(Car::from)
                .collect(collectingAndThen(toList(), Cars::new));
    }

    public void runRound(TryCount tryCount) {
        for (int i = 0; i < tryCount.getValue(); i++) {
            runRound();
        }
    }

    private void runRound() {
        for (Car car : cars) {
            int randomNumber = random.nextInt(RANDOM_NUM_MAX_VALUE);
            car.runForward(randomNumber);
        }
    }

    public List<String> getWinner() {
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.isSamePosition(maxPosition))
                .map(Car::getNameValue)
                .collect(toUnmodifiableList());
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPositionValue)
                .max()
                .orElse(-1);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
