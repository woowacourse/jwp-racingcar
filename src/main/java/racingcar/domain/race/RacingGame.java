package racingcar.domain.race;

import java.util.List;
import racingcar.domain.RandomNumberPicker;
import racingcar.domain.car.Car;
import racingcar.domain.car.Winners;

public class RacingGame {

    private static final int TRIAL_COUNT_BOUND = 0;
    private final RacingCars racingCars;

    public RacingGame(List<String> carNames) {
        this.racingCars = new RacingCars(carNames);
    }

    public void progress(int trialCount) {
        validateTrialCount(trialCount);
        for (int count = 0; count < trialCount; count++) {
            racingCars.moveCars(new RandomNumberPicker());
        }
    }

    private void validateTrialCount(int trialCount) {
        if (trialCount <= TRIAL_COUNT_BOUND) {
            throw new IllegalArgumentException("시도 횟수는 0번 보다 커야 합니다.");
        }
    }

    public Winners getWinners() {
        return Winners.from(racingCars);
    }

    public List<Car> getRacingCars() {
        return racingCars.getCars();
    }

}
