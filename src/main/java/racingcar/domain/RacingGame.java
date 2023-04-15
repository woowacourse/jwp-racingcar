package racingcar.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class RacingGame {

    private static final String CAR_NAME_DELIMITER = ",";

    public Cars createCars(final List<String> carNames) {
        return new Cars(carNames);
    }

    public Cars createCars(final String names) {
        List<String> carNames = trimNames(names);

        return createCars(carNames);
    }

    private List<String> trimNames(final String names) {
        return Arrays.stream(names.split(CAR_NAME_DELIMITER))
                .map(String::trim)
                .collect(toList());
    }

    public TrialCount createTrialCount(final int inputValue) {
        return new TrialCount(inputValue);
    }

    public void playRacing(final Cars cars, final TrialCount trialCount) {
        for (int i = 0; i < trialCount.getValue(); i++) {
            cars.moveAll();
        }
    }
}
