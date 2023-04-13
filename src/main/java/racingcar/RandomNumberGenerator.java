package racingcar;

import org.springframework.stereotype.Component;

@Component
public interface RandomNumberGenerator {

    int generate();
}
