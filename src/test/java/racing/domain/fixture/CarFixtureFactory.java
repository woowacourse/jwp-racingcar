package racing.domain.fixture;

import java.util.Arrays;
import java.util.stream.Collectors;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;

public class CarFixtureFactory {
    private CarFixtureFactory() {
    }

    public static Car getCarWithNameAndStep(String carName, int step) {
        return new Car(new CarName(carName), step);
    }

    public static Car getCarWithName(String carName) {
        return new Car(new CarName(carName));
    }

    public static Cars getCarsWithName(String... carName) {
        return new Cars(Arrays.stream(carName)
                .map(CarName::new)
                .map(Car::new)
                .collect(Collectors.toList()));
    }
}
