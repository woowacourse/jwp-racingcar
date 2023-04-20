package racingcar.domain;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return (int)(Math.random() * 10);
    }
}
