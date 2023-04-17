package racing.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    // 테스트를 위한 메서드
    public Car getCar(int n) {
        return cars.get(n);
    }

    public void calculator(Random random) {
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
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getStep() == winnerStep) {
                winners.add(car.getName());
            }
        }
        return winners;
    }

    public void moveCars(int count) {
        while (count-- > 0) {
            this.calculator(new Random());
        }
    }

    public List<Car> getCars() {
        return cars;
    }
}
