package racingcar.domain;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cars {

    private static final int MIN_CAR_COUNT = 2;

    private final List<Car> cars;

    public Cars(final List<String> carNames) {
        validate(carNames);

        this.cars = carNames.stream()
                .map(Car::new)
                .collect(toList());
    }

    private void validate(final List<String> carNames) {
        validateCount(carNames);
        validateNameDuplicate(carNames);
    }

    private void validateCount(final List<String> carNames) {
        if (carNames.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException("최소 " + MIN_CAR_COUNT + "개 이상의 자동차를 입력해주세요.");
        }
    }

    private void validateNameDuplicate(final List<String> cars) {
        Set<String> nonDuplicateNames = new HashSet<>(cars);

        if (nonDuplicateNames.size() != cars.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    public void moveAll() {
        cars.stream()
                .filter(MovableStrategy::isMove)
                .forEach(Car::move);
    }

    public List<Car> getWinner() {
        int winnerDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElseThrow();
        return cars.stream()
                .filter(car2 -> car2.getDistance() == winnerDistance)
                .collect(toList());
    }

    public List<Car> getCars() {
        return cars;
    }
}
