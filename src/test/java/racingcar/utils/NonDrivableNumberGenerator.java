package racingcar.utils;

import racingcar.utils.NumberGenerator;

public class NonDrivableNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return 3;
    }
}
