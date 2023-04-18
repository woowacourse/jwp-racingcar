package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cars {
    private static final int RANDOM_NUM_MAX_VALUE = 10;
    private static final String DELIMITER = ",";
    private static final Random random = new Random();

    private final List<Car> cars;

    public Cars(String carNames) {
        this(Stream.of(carNames.split(DELIMITER))
                .map(Car::new)
                .collect(Collectors.toList()));
    }

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void runRound() {
        for (Car car : cars) {
            int randomNumber = random.nextInt(RANDOM_NUM_MAX_VALUE);
            car.runForward(randomNumber);
        }
    }

    public List<String> getWinner() {
        int maxDistance = findMaxDistance();
        return cars.stream().filter(car -> car.isSameDistance(maxDistance))
                .map(Car::getName)
                .map(Name::getValue)
                .collect(Collectors.toList());
    }

    private int findMaxDistance() {
        return cars.stream()
                .mapToInt(car -> car.getDistance().getValue())
                .max()
                .orElse(-1);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
