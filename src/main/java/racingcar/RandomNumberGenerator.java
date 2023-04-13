package racingcar;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator implements NumberGenerator {
    private static final int MULTIPLIER = 10;

    @Override
    public int generate() {
        return (int) (Math.random() * MULTIPLIER);
    }
}
