package racingcar.domain;

public class NonDrivableNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return 3;
    }
}
