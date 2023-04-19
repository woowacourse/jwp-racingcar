package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//todo 4 : RacingCar 대신에, List<Car>을 만들어준다. 객체 생성의 책임을 외부로 돌리기 위함인데, 이게 맞나?
public class CarFactory {

    public static List<Car> generate(List<String> input, int startPoint) {
        final List<String> carNames = new ArrayList<>(input);
        validateDuplicatedCarNames(carNames);

        return createCarsByNames(carNames, startPoint);
    }

    private static List<Car> createCarsByNames(List<String> carNames, int startPoint) {
        return carNames.stream()
                .map(carName -> Car.of(carName, startPoint))
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validateDuplicatedCarNames(List<String> carNames) {
        if (Set.copyOf(carNames).size() != carNames.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복되지 않아야 합니다.");
        }
    }

}
