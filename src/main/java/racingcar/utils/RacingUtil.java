package racingcar.utils;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.vo.CarName;
import racingcar.vo.Trial;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RacingUtil {
    public static final String DUPLICATING_NAME_EXCEPTION_MESSAGE = "중복된 이름은 사용할 수 없습니다.";
    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "입력값은 비어있을 수 없습니다.";
    private RacingUtil() {
    }

    public static Cars race(String names, int count) {
        Cars cars = initializeCars(names);
        playGame(cars, Trial.of(count));
        return cars;
    }

    private static Cars playGame(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
        return cars;
    }

    private static Cars initializeCars(String names) {
        List<CarName> carNames = getCarNames(names);
        validateDuplication(carNames);
        return saveCars(carNames);
    }

    private static List<CarName> getCarNames(String names) {
        List<CarName> carNames = CarName.of(
                Arrays.asList(names.split(","))
        );

        validateCarNames(carNames);
        return carNames;
    }

    private static void validateCarNames(List<CarName> carNames) {
        hasNoNames(carNames);

        for (CarName carName : carNames) {
            isNameBlank(carName);
        }
    }

    private static void hasNoNames(List<CarName> carNames) {
        if (carNames.size() == 0) {
            throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static void isNameBlank(CarName carName) {
        if (carName.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static Cars saveCars(List<CarName> carNames) {
        List<Car> cars = carNames.stream().map(Car::of)
                .collect(Collectors.toList());
        return new Cars(cars, RandomNumberGenerator.makeInstance());
    }

    private static void validateDuplication(List<CarName> names) {
        Set<CarName> namesWithoutDuplication = new HashSet<>(names);
        if (names.size() != namesWithoutDuplication.size()) {
            throw new IllegalArgumentException(DUPLICATING_NAME_EXCEPTION_MESSAGE);
        }
    }
}
