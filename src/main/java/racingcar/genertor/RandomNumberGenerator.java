package racingcar.genertor;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator implements NumberGenerator {
    private Random random = new Random();

    @Override
    public int getNum() {
        return random.nextInt(9);
    }
}
