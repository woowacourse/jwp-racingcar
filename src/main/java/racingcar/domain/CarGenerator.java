package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.view.Validator;

public class CarGenerator {
    private static final Validator validator = new Validator();

    public List<Car> generateCars(String[] carNames) {
        validator.checkValidCarNames(carNames);
        return Arrays.stream(carNames)
                .map(Car::new)
                .collect(Collectors.toList());
    }
}
