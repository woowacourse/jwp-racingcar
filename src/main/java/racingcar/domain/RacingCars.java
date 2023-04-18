package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCars {

    private static final int SORTED_CARS_HEAD_INDEX = 0;
    private final List<Car> racingCars;

    public RacingCars(final List<Car> racingCars) {
        this.racingCars = racingCars;
    }

    public void process(final NumberGenerator powerValueGenerator) {
        racingCars.forEach(car -> car.moveOrStayByPower(powerValueGenerator.generate()));
    }

    public List<String> findWinningCarNames() {
        final List<Car> sortedCars = new ArrayList<>(racingCars);
        sortedCars.sort(Collections.reverseOrder());

        return racingCars.stream()
                .filter(racingCar -> racingCar.hasSamePosition(sortedCars.get(SORTED_CARS_HEAD_INDEX)))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public List<Car> racingCars() {
        return Collections.unmodifiableList(racingCars);
    }
}
