package racing.domain;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final SecureRandom random = new SecureRandom();

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
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
