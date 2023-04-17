package racingcar.domain.car;

import java.util.List;
import java.util.stream.Collectors;

public class CarFactory {

    public Cars createCars(final List<String> carNames) {
        return new Cars(makeCars(carNames));
    }

    private List<Car> makeCars(final List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
