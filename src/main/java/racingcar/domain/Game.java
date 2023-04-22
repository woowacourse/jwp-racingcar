package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.exception.IllegalGameArgumentException;
import racingcar.exception.IllegalGameStateException;

public class Game {

    private final List<Car> cars;
    private final TrialCount initialTrialCount;
    private TrialCount remainingTrialCount;

    public Game(List<Car> cars, int trialCount) {
        this(cars, trialCount, trialCount);
    }

    public Game(List<Car> cars, int trialCount, int remainingTrialCount) {
        validateCarsLength(cars);
        validateNoDuplicateCar(cars);
        this.cars = new ArrayList<>(cars);
        this.initialTrialCount = new TrialCount(trialCount);
        this.remainingTrialCount = new TrialCount(remainingTrialCount);
    }

    public void playOnceWith(MoveChance moveChance) {
        for (Car car : cars) {
            car.move(moveChance);
        }
        remainingTrialCount = remainingTrialCount.decrease();
    }

    public boolean isInProgress() {
        return remainingTrialCount.isLeft();
    }

    public List<Car> findWinners() {
        Car farthestCar = findFarthestCar();
        return cars.stream()
                .filter(car -> car.hasSamePositionWith(farthestCar))
                .collect(Collectors.toList());
    }

    private Car findFarthestCar() {
        return cars.stream()
                .reduce(this::getFartherCar)
                .orElseThrow(() -> new IllegalGameStateException("차량이 없습니다"));
    }

    private Car getFartherCar(Car car, Car other) {
        if (car.isFartherThan(other)) {
            return car;
        }
        return other;
    }

    private void validateNoDuplicateCar(List<Car> cars) {
        if (cars.size() > new HashSet<>(cars).size()) {
            throw new IllegalGameArgumentException("자동차 이름은 중복되면 안됩니다");
        }
    }

    private void validateCarsLength(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalGameArgumentException("차량이 없습니다");
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }


    public int getInitialTrialCount() {
        return initialTrialCount.getCount();
    }

    public int getRemainingTrialCount() {
        return remainingTrialCount.getCount();
    }
}
