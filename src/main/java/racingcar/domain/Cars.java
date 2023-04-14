package racingcar.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {

    private static final int MIN_CAR_COUNT = 2;

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validateCount(cars);
        validateNameDuplicate(cars);
        this.cars = cars;
    }

    private void validateCount(final List<Car> cars) {
        if (cars.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException("최소 " + MIN_CAR_COUNT +"개 이상의 자동차를 입력해주세요.");
        }
    }

    private void validateNameDuplicate(final List<Car> cars) {
        List<String> carNames = cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());

        Set<String> nonDuplicateNames = new HashSet<>(carNames);

        if (nonDuplicateNames.size() != carNames.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    public void moveAll() {
        cars.stream()
                .filter(MovableStrategy::isMove)
                .forEach(Car::updateDistance);
    }

    public List<Car> getWinner() {
        int winnerDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElseThrow();
        return cars.stream()
                .filter(car2 -> car2.getDistance() == winnerDistance)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
    }
}
