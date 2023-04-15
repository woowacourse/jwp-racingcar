package racingcar.model;

import java.util.HashMap;
import java.util.Map;
import racingcar.util.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars = new ArrayList<>();

    public Cars(List<String> carsName) {
        validateNameDuplication(carsName);
        for (String name : carsName) {
            cars.add(new Car(name));
        }
    }

    private void validateNameDuplication(final List<String> carsName) {
        Map<String, Integer> countByName = new HashMap<>();
        for (String carName : carsName) {
            countByName.put(carName, countByName.getOrDefault(carName, 0) + 1);
        }
        if (countByName.size() != carsName.size()) {
            throw new IllegalArgumentException("중복되는 이름이 존재합니다.");
        }
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }

    public void moveResult(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.moveByNumber(numberGenerator.generateNumber());
        }
    }

    public List<String> getWinners() {
        return cars.stream().filter(car -> car.checkPositionEqual(getMaxPosition()))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private Car getMaxPosition() {
        return cars.stream()
                .max(Car::compareTo)
                .get();
    }
}
