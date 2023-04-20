package racingcar.domain.numbergenerator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomSingleDigitGenerator implements NumberGenerator {

    public static final int BOUND = 10;
    private final Random random = new Random();

    @Override
    public int generate() {
        return random.nextInt(BOUND);
    }
}
