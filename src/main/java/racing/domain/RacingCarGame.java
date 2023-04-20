package racing.domain;

import racing.domain.utils.RacingCarNumberGenerator;

public class RacingCarGame {

    private final Cars cars;
    private final int roundCount;

    public RacingCarGame(Cars cars, int roundCount) {
        this.cars = cars;
        this.roundCount = roundCount;
    }

    public void playRounds(RacingCarNumberGenerator numberGenerator) {
        for (int i = 0; i < roundCount; i++) {
            cars.moveCarsWith(numberGenerator);
        }
    }

    public Cars winnerCars() {
        return cars.filterMaxStepCars();
    }
}
