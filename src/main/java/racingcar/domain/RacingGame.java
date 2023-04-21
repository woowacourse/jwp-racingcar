package racingcar.domain;

import racingcar.util.NumberGenerator;

import java.util.List;

public class RacingGame {

    private final RacingCars racingCars;
    private final NumberGenerator numberGenerator;
    private final Integer trialCount;

    public RacingGame(final RacingCars racingCars, final NumberGenerator numberGenerator, final Integer trialCount) {
        this.racingCars = racingCars;
        this.numberGenerator = numberGenerator;
        this.trialCount = trialCount;
    }

    public static RacingGame readyToRacingGame(
            final String names,
            final NumberGenerator numberGenerator,
            final Integer trialCount
    ) {
        return new RacingGame(RacingCars.makeCars(names), numberGenerator, trialCount);
    }

    public void play() {
        racingCars.moveAllCars(trialCount, numberGenerator);
    }

    public List<Car> determineWinners() {
        return racingCars.getWinners();
    }

    public List<Car> getParticipantAllCar() {
        return racingCars.getCars();
    }

    public Integer getTrialCount() {
        return trialCount;
    }
}
