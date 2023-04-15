package racingcar.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private static final int MINIMUM_CAR_COUNT = 2;

    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        validateDuplicatedNames(cars);
        validateCarCount(cars.size());
        this.cars = cars;
    }

    public void moveCars(final NumberGenerator numberGenerator) {
        int moveNumber = numberGenerator.generate();
        cars.forEach(car -> car.move(moveNumber));
    }

    public List<String> calculateWinners() {
        Position maxPosition = cars.stream()
                .map(Car::getCurrentPosition)
                .max(Comparator.comparingInt(Position::getPosition))
                .orElseGet(Position::new);

        List<String> winners = cars.stream()
                .filter(car -> car.getCurrentPosition().equals(maxPosition))
                .map(winnerCar -> winnerCar.getCarName().getName())
                .collect(Collectors.toList());

        return winners;
    }

    private void validateDuplicatedNames(final List<Car> cars) {
        List<Car> uniqueCars = cars.stream()
                .distinct()
                .collect(Collectors.toUnmodifiableList());

        if (uniqueCars.size() != cars.size()) {
            throw new IllegalArgumentException("자동차 이름이 중복됩니다.");
        }
    }

    private void validateCarCount(final int size) {
        if (size < MINIMUM_CAR_COUNT) {
            throw new IllegalArgumentException("자동차 수는 2대 이상이어야 합니다.");
        }
    }

    public List<Car> getLatestResult() {
        return new ArrayList<>(cars);
    }
}
