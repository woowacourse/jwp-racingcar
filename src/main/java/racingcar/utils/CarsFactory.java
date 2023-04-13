package racingcar.utils;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Cars;

public class CarsFactory {

    private static final int DEFAULT_DISTANCE_VALUE = 0;

    public static Cars createCars(final List<String> carNames) {
        List<Car> cars = makeCars(carNames);
        return new Cars(cars);
    }

    private static List<Car> makeCars(final List<String> carNames) {
        return carNames.stream()
                .map(CarsFactory::makeCar)
                .collect(Collectors.toList());
    }

    private static Car makeCar(final String carName) {
        return new Car(carName, DEFAULT_DISTANCE_VALUE);
    }
}
