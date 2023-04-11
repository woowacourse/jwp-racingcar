package racingcar.domain;

import java.util.Random;
import racingcar.domain.race.NumberPicker;

public class RandomNumberPicker implements NumberPicker {
    Random random = new Random();
    public static final int BOUND = 10;

    @Override
    public int pickNumber() {
        return random.nextInt(BOUND);
    }
}
