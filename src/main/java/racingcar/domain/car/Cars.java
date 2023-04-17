package racingcar.domain.car;

import java.util.List;
import racingcar.domain.game.NumberGenerator;
import racingcar.exception.CarsException;

public class Cars {

    private static final int MINIMUM_SIZE = 2;
    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        validate(cars);
        this.cars = List.copyOf(cars);
    }

    private void validate(final List<Car> cars) {
        if (isOutOfSize(cars)) {
            throw new CarsException("[ERROR] 자동차는 2대 이상 입력되어야 합니다.");
        }
        if (hasDuplication(cars)) {
            throw new CarsException("[ERROR] 자동차 이름은 중복될 수 없습니다.");
        }
    }

    private boolean isOutOfSize(final List<Car> cars) {
        return cars.size() < MINIMUM_SIZE;
    }

    private boolean hasDuplication(final List<Car> cars) {
        return cars.stream()
                .distinct()
                .count() != cars.size();
    }

    public void moveBy(final NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.move(numberGenerator.generate());
        }
    }

    public List<Car> getCars() {
        return cars;
    }
}
