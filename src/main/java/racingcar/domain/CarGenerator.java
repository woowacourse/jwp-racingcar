package racingcar.domain;

import racingcar.view.Validator;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CarGenerator {
    private static final Validator validator = new Validator();

    public List<Car> generateCars(String[] carNames) {
        validator.checkValidCarNames(carNames);
        return Arrays.stream(carNames)
                .map(Car::new)
                .collect(toList());
    }
}
