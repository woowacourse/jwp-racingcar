package racingcar.domain;


import java.util.ArrayList;
import java.util.List;

public class CarsFactory {
    private CarsFactory() {
    }

    public static Cars create(List<String> carNames) {
        List<Car> cars = new ArrayList<>();
        for (String carName : carNames) {
            cars.add(new Car(carName,0));
        }
        return new Cars(cars);
    }
}
