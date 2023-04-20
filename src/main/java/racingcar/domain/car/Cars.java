package racingcar.domain.car;

import lombok.Getter;
import racingcar.domain.strategy.move.MoveStrategy;

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
        final List<String> splitedNames = List.of(names.split(","));
        validateHomonymNames(splitedNames);
        
        return splitedNames.stream()
                .map(Name::new)
                .map(Car::new)
                .collect(Collectors.toList());
    }
    
    private void validateHomonymNames(final List<String> splitedNames) {
        final long count = distinctCount(splitedNames);
        final int size = splitedNames.size();
        
        if (size != count) {
            throw new IllegalArgumentException("[ERROR] 동명이인은 불가능합니다. 입력된 차 이름 목록 : " + splitedNames);
        }
    }
    
    private static long distinctCount(final List<String> splitedNames) {
        return splitedNames.stream()
                .distinct()
                .count();
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
