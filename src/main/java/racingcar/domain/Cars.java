package racingcar.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import racingcar.utils.RandomPowerGenerator;

public class Cars {

    private static final int DEFAULT_DISTANCE_VALUE = 0;

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        validate(cars);
        this.cars = cars;
    }

    public static Cars from(final List<String> carNames) {
        List<Car> cars = makeCars(carNames);
        return new Cars(cars);
    }

    private static List<Car> makeCars(final List<String> carNames) {
        return carNames.stream()
                .map(carName -> new Car(carName, DEFAULT_DISTANCE_VALUE))
                .collect(Collectors.toList());
    }

    private void validate(List<Car> cars) {
        List<String> carNames = cars.stream()
                .map(Car::getCarName)
                .collect(Collectors.toList());

        Set<String> carNameWithoutDuplication = new HashSet<>(carNames);

        if (carNameWithoutDuplication.size() != carNames.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복되지 않아야합니다.");
        }
    }

    public void moveAll(final RandomPowerGenerator randomPowerGenerator) {
        for (final Car car : cars) {
            final int power = randomPowerGenerator.generateRandomPower();
            car.move(power);
        }
    }

    public List<String> getWinnerNames() {
        List<String> winnerNames = new ArrayList<>();
        final int maxCountOfDistance = findMaxCountOfDistance();

        for (final Car car : cars) {
            addWinnerName(winnerNames, car, maxCountOfDistance);
        }

        return winnerNames;
    }

    public boolean isWinner(final String name) {
        return getWinnerNames().contains(name);
    }

    private int findMaxCountOfDistance() {
        int maxCountOfDistance = Integer.MIN_VALUE;

        for (final Car car : cars) {
            maxCountOfDistance = Math.max(car.getDistance(), maxCountOfDistance);
        }

        return maxCountOfDistance;
    }

    private void addWinnerName(final List<String> winnerNames, final Car car, final int maxCountOfDistance) {
        if (car.getDistance() == maxCountOfDistance) {
            winnerNames.add(car.getCarName());
        }
    }

    public List<Car> getCars() {
        return cars;
    }
}
