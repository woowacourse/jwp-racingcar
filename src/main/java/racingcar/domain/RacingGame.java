package racingcar.domain;

import java.util.List;
import racingcar.utils.NumberGenerator;

public class RacingGame {
    private final NumberGenerator numberGenerator;
    private final RacingCars racingCars;

    public RacingGame(NumberGenerator numberGenerator, RacingCars racingCars) {
        this.numberGenerator = numberGenerator;
        this.racingCars = racingCars;
    }

    public void moveCars() {
        racingCars.moveCars(numberGenerator);
    }

    public List<String> getWinners() {
        return racingCars.getWinners();
    }
}
