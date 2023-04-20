package racing.domain;

import racing.domain.utils.RacingCarNumberGenerator;

public class RacingCarGame {

    private final Cars cars;
    private final TrialCount trialCount;

    public RacingCarGame(Cars cars, TrialCount trialCount) {
        this.cars = cars;
        this.trialCount = trialCount;
    }

    public void playRounds(RacingCarNumberGenerator numberGenerator) {
        for (int i = 0; i < trialCount.getValue(); i++) {
            cars.moveCarsWith(numberGenerator);
        }
    }

    public Cars winnerCars() {
        return cars.filterMaxStepCars();
    }

    public Cars getCars() {
        return cars;
    }
}
