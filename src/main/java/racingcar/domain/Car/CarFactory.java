package racingcar.domain.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarFactory {

    private CarFactory() {

    }

    public static List<Car> generate(List<String> input, int startPoint) {
        List<String> carNames = new ArrayList<>(input);
        validateDuplicatedCarNames(carNames);

        return createCarsByNames(carNames, startPoint);
    }

    private static void validateDuplicatedCarNames(List<String> carNames) {
        if (Set.copyOf(carNames).size() != carNames.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복되지 않아야 합니다.");
        }
    }

    private static List<Car> createCarsByNames(List<String> carNames, int startPoint) {
        return carNames.stream()
                .map(carName -> new Car(carName, startPoint))
                .collect(Collectors.toUnmodifiableList());
    }
}
