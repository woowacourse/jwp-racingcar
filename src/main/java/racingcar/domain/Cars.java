package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.utils.NumberGenerator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;
    private final NumberGenerator numberGenerator;

    public Cars(List<Car> cars, NumberGenerator numberGenerator) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
    }

    private static boolean hasHighestPosition(int highestPosition, Car car) {
        return car.hasPosition(highestPosition);
    }

    public List<CarDto> getCarDtos() {
        return cars.stream().map(CarDto::new)
                .collect(Collectors.toList());
    }

    public void saveCar(Car car) {
        cars.add(car);
    }

    public void move() {
        cars.forEach((car) -> car.move(numberGenerator.generateNumber()));
    }

    public List<String> getWinnerNames() {
        int highestPosition = calculateHighestPosition();
        return cars.stream()
                .filter(car -> hasHighestPosition(highestPosition, car))
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private int calculateHighestPosition() {
        return Collections.max(cars.stream()
                .map((Car::getPosition))
                .collect(Collectors.toUnmodifiableList()));
    }

    public boolean isWinnerContaining(String name) {
        return getWinnerNames().contains(name);
    }

    @Override
    public String toString() {
        return "Cars{" +
                "cars=" + cars +
                ", randomNumberGenerator=" + numberGenerator +
                '}';
    }
}
