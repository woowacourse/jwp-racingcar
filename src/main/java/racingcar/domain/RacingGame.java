package racingcar.domain;

import java.util.Collections;
import java.util.List;

public class RacingGame {

    private static final int POWER_VALUE_MIN = 0;
    private static final int POWER_VALUE_MAX = 9;

    private final NumberGenerator powerValueGenerator;
    private RacingCars racingCars;

    private RacingGame(RacingCars racingCars) {
        this.powerValueGenerator = new RandomNumberGenerator(POWER_VALUE_MIN, POWER_VALUE_MAX);
        this.racingCars = racingCars;
    }

    public static RacingGame of(List<String> racingCarNames) {
        RacingCars racingCars = new RacingCars(racingCarNames);

        return new RacingGame(racingCars);
    }

    public void race(final int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            racingCars.process(powerValueGenerator);
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(racingCars.racingCars());
    }

    public List<String> getWinnerNames() {
        return racingCars.findHeadCarNames();
    }
}
