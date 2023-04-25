package racingcar;

import racingcar.domain.NumberGenerator;

public class MockNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return 5;
    }
}
