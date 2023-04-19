package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        validateSize(cars);
        validateNotDuplicateCarName(cars);
        this.cars = cars;
    }

    private void validateSize(final List<Car> cars) {
        if (cars.size() < 2) {
            throw new IllegalArgumentException("2개 이상의 자동차로 게임이 가능합니다.");
        }
    }

    private void validateNotDuplicateCarName(final List<Car> cars) {
        int distinctSize = (int) cars.stream()
                .map(Car::getName)
                .distinct()
                .count();
        if (distinctSize < cars.size()) {
            throw new IllegalArgumentException("중복된 이름은 입력할 수 없습니다.");
        }
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
