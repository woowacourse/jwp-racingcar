package racingcar.domain.Car;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator implements NumberGenerator {

    private static final int MINIMUM_NUMBER_OF_ENGINE = 0;
    private static final int MAXIMUM_NUMBER_OF_ENGINE = 9;

    @Override
    public int generate() {
        return (int) (Math.random() * (MAXIMUM_NUMBER_OF_ENGINE - MINIMUM_NUMBER_OF_ENGINE + 1))
                + MINIMUM_NUMBER_OF_ENGINE;
    }
}
