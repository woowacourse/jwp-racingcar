package racingcar.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCarNames {

    private final List<String> carNames;

    public RacingCarNames(final List<String> carNames) {
        validateCarNames(carNames);
        this.carNames = carNames;
    }

    private void validateCarNames(final List<String> carNames) {
        if (carNames.size() != new HashSet<>(carNames).size()) {
            throw new IllegalArgumentException("중복된 차량 이름이 존재합니다.");
        }
        if (carNames.size() == 1) {
            throw new IllegalArgumentException("차량이 둘 이상이어야 경주를 진행할 수 있습니다.");
        }
    }

    public List<Car> createCars() {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }
}
