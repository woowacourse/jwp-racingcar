package racingcar.domain;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int MIN_NUMBER = 0;
    private static final int MAXIMUM_NUMBER = 9;

    @Override
    public int generate() {
        return (int) (Math.random() * MAXIMUM_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }
}
