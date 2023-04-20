package racingcar.domain;

import java.util.Random;
import racingcar.domain.race.NumberPicker;

public class RandomNumberPicker implements NumberPicker {
    private static final int BOUND = 10;
    private final Random random = new Random();

    @Override
    public int pickNumber() {
        return random.nextInt(BOUND);
    }
}
