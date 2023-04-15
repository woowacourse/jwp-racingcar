package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(List<String> inputCarNames) {
        List<Car> cars = inputCarNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> findWinner() {
        final int winnerPosition = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
        return cars.stream()
                .filter(car -> car.getPosition() == winnerPosition)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    private void addWinnerName(List<String> winnerNames, int maxPosition, Car car) {
        if (car.getPosition() == maxPosition) {
            winnerNames.add(car.getName());
        }
    }
}
