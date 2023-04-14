package racingcar.domain;

public class NonMovableNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return 3;
    }
}
