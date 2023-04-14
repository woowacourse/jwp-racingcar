package racingcar.domain;

import racingcar.domain.numbergenerator.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Cars {

    private static final int ONLY_ONE_CAR = 1;

    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        validateDuplicatedName(cars);
        validateSoloPlay(cars);
        this.cars = cars;
    }

    public Cars(final String[] names) {
        this(stream(names)
                .map(Car::new)
                .collect(Collectors.toList())
        );
    }

    private void validateSoloPlay(final List<Car> cars) {
        if (cars.size() == ONLY_ONE_CAR) {
            throw new IllegalArgumentException("[ERROR] 차를 둘 이상 입력하세요.");
        }
    }

    private void validateDuplicatedName(final List<Car> cars) {
        int carsSize = cars.size();
        int duplicateRemovedCount =
                (int) cars.stream()
                        .map(Car::getCarName)
                        .map(String::trim)
                        .distinct()
                        .count();
        if (carsSize != duplicateRemovedCount) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름을 사용할 수 없습니다.");
        }
    }

    public void moveCars(NumberGenerator numberGenerator) {

        cars.forEach(car -> car.move(numberGenerator.generateNumber()));
    }

    public List<Car> getCars() {
        return cars;
    }
}
