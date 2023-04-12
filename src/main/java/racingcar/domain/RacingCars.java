package racingcar.domain;

import racingcar.util.NumberGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RacingCars {

    private static final String EMPTY_CARS_ERROR_MESSAGE = "[ERROR] 경주할 자동차 리스트가 비었습니다.";
    private static final String CAR_NAMES_DUPLICATE_ERROR = "[ERROR] 경주할 자동차 이름이 중복되었습니다.";
    private static final String CAR_NAMES_DELIMITER = ",";
    private static final int START_POSITION = 0;

    private final List<Car> cars;

    private RacingCars(List<Car> cars) {
        this.cars = cars;
    }

    public static RacingCars makeCars(final String names) {
        String[] splitCarNames = getSplitCarNames(names);
        validateSplitCarNames(splitCarNames);

        return new RacingCars(Arrays.stream(splitCarNames)
                                    .map(carName -> new Car(carName, START_POSITION))
                                    .collect(Collectors.toUnmodifiableList()));
    }

    private static String[] getSplitCarNames(String carNames) {
        return carNames.split(CAR_NAMES_DELIMITER, -1);
    }

    private static void validateSplitCarNames(String[] splitCarNames) {
        if (isDuplicated(splitCarNames)) {
            throw new IllegalArgumentException(CAR_NAMES_DUPLICATE_ERROR);
        }
    }

    private static boolean isDuplicated(String[] splitCarNames) {
        Set<String> removedDuplicatedSplitCarNames = Arrays.stream(splitCarNames)
                                                           .collect(Collectors.toSet());
        return splitCarNames.length != removedDuplicatedSplitCarNames.size();
    }

    public void moveAllCars(final int trialCount, final NumberGenerator numberGenerator) {
        for (int repeat = 0; repeat < trialCount; repeat++) {
            moveCar(numberGenerator);
        }
    }

    private void moveCar(final NumberGenerator numberGenerator) {
        for (Car car : cars) {
            int randomValue = numberGenerator.generate();
            car.move(randomValue);
        }
    }

    public List<Car> getWinners() {
        Car maxPositionCar = getMaxPositionCar();
        return getMaxPositionCars(maxPositionCar);
    }

    private Car getMaxPositionCar() {
        return cars.stream()
                   .max(Car::compareTo)
                   .orElseThrow(() -> new IllegalStateException(EMPTY_CARS_ERROR_MESSAGE));
    }

    private List<Car> getMaxPositionCars(Car maxPositionCar) {
        return cars.stream()
                   .filter(car -> car.isSamePositionCar(maxPositionCar))
                   .collect(Collectors.toUnmodifiableList());
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
