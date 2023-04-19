package racingcar.domain.car;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import racingcar.domain.strategy.move.MoveStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Cars {
    private final List<Car> cars;
    
    public Cars(final String names) {
        this.cars = initCars(names);
    }
    
    private List<Car> initCars(final String names) {
        return Arrays.stream(names.split(","))
                .map(Name::new)
                .map(Car::new)
                .collect(Collectors.toList());
    }
    
    public void move(final MoveStrategy moveStrategy) {
        for (Car car : cars) {
            int index = cars.indexOf(car);
            Car movedCar = car.move(moveStrategy);
            
            cars.set(index, movedCar);
        }
    }
}
