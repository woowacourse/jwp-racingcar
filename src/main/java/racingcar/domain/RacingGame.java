package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {

    private static final int POWER_VALUE_MIN = 0;
    private static final int POWER_VALUE_MAX = 9;

    private final NumberGenerator powerValueGenerator = new RandomNumberGenerator(POWER_VALUE_MIN, POWER_VALUE_MAX);
    private RacingCars racingCars;

    private RacingGame(final RacingCars racingCars) {
        this.racingCars = racingCars;
    }

    public static RacingGame of(final List<Car> racingCars) {
        return new RacingGame(new RacingCars(racingCars));
    }

    public void play(final int racingCount) {
        race(racingCount);
    }

    private void race(final int count) {
        for (int i = 0; i < count; i++) {
            racingCars.process(powerValueGenerator);
        }
    }

    public List<Car> racingCars() {
        return new ArrayList<>(racingCars.racingCars());
    }

    public List<String> findWinningCarNames() {
        return racingCars.findWinningCarNames();
    }

}
