package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class CarFactory {

    public static Cars buildCars(final List<String> carNames) {
        return new Cars(
            carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList())
        );
    }
}
