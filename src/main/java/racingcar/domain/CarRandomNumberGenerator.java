package racingcar.domain;

import org.springframework.stereotype.Component;
import racingcar.RandomNumberGenerator;

@Component
public class CarRandomNumberGenerator implements RandomNumberGenerator {
    private static final int BOUND = 10;

    @Override
    public int generate() {
        return (int) (Math.random() * BOUND);
    }
}
