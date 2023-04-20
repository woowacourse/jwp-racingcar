package racingcar.domain;

import racingcar.exception.IllegalGameArgumentException;
import racingcar.exception.IllegalGameStateException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final List<Car> cars;
    private final TrialCount initialTrialCount;
    private TrialCount trialCount;

    public Game(final List<Car> cars, final int trialCount) {
        validateCarsLength(cars);
        validateNoDuplicateCar(cars);
        this.cars = new ArrayList<>(cars);
        this.initialTrialCount = new TrialCount(trialCount);
        this.trialCount = new TrialCount(trialCount);
    }

    public void playOnceWith(MoveChance moveChance) {
        for (final Car car : cars) {
            car.move(moveChance);
        }
        trialCount = trialCount.decrease();
    }

    public boolean isNotDone() {
        return trialCount.isLeft();
    }

    public List<Car> findWinners() {
        final Car farthestCar = findFarthestCar();
        return cars.stream()
                .filter(car -> car.hasSamePositionWith(farthestCar))
                .collect(Collectors.toList());
    }

    private Car findFarthestCar() {
        return cars.stream()
                .reduce(this::getFartherCar)
                .orElseThrow(() -> new IllegalGameStateException("차량이 없습니다"));
    }

    private Car getFartherCar(final Car car, final Car other) {
        if (car.isFartherThan(other)) {
            return car;
        }
        return other;
    }

    private void validateNoDuplicateCar(final List<Car> cars) {
        if (cars.size() > new HashSet<>(cars).size()) {
            throw new IllegalGameArgumentException("자동차 이름은 중복되면 안됩니다");
        }
    }

    private void validateCarsLength(final List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalGameArgumentException("차량이 없습니다");
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public int getTrialCount() {
        return initialTrialCount.getCount() - trialCount.getCount();
    }
}
