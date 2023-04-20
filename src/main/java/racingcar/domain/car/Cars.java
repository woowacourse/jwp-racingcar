package racingcar.domain.car;

import lombok.Getter;
import racingcar.domain.strategy.move.MoveStrategy;

import java.util.Arrays;
import java.util.Comparator;
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
            final int index = cars.indexOf(car);
            final Car movedCar = car.move(moveStrategy);
            
            cars.set(index, movedCar);
        }
    }
    
    public List<Car> getWinners() {
        final Car maxPositionCar = getMaxPositionCar();
        
        return cars.stream()
                .filter(car -> car.isSamePositionTo(maxPositionCar))
                .collect(Collectors.toUnmodifiableList());
    }
    
    private Car getMaxPositionCar() {
        return cars.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 차가 존재하지 않습니다."));
    }
}
