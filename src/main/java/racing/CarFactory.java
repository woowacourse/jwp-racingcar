package racing;

import racing.domain.Car;
import racing.domain.Cars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarFactory {

    public static Cars carFactory(List<String> names) {
        return new Cars(convertToCars(names));
    }

    public static Cars carFactory(String names) {
        return carFactory(Arrays.asList(names.split(",")));
    }

    private static List<Car> convertToCars(List<String> names) {
        final List<Car> cars = new ArrayList<>();
        for (String carName : names) {
            cars.add(new Car(carName));
        }
        return cars;
    }

}
