package racing.domain;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final String CAR_NAME_DUPLICATED = "중복된 자동차 이름이 존재합니다.";
    private static final SecureRandom random = new SecureRandom();

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validateDuplication(cars);
        this.cars = cars;
    }

    private void validateDuplication(List<Car> cars) {
        long distinctCount = (int) cars.stream()
                .map(Car::getName)
                .distinct()
                .count();

        if (distinctCount != cars.size()) {
            throw new IllegalArgumentException(CAR_NAME_DUPLICATED);
        }
    }

    public void moveCarsByCount(int count) {
        for (int i = 0; i < count; i++) {
            moveCars();
        }
    }

    private void moveCars() {
        cars.stream()
                .filter(car -> 4 <= random.nextInt(10))
                .forEach(Car::moveForward);
    }

    public List<String> getWinners() {
        int winnerStep = getWinnerStep();
        return findWinners(winnerStep);
    }

    private int getWinnerStep() {
        return cars.stream()
                .mapToInt(Car::getStep)
                .max()
                .orElseThrow();
    }

    private List<String> findWinners(int winnerStep) {
        return cars.stream()
                .filter(car -> car.getStep() == winnerStep)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
    }
}
