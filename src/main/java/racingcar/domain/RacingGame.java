package racingcar.domain;

public final class RacingGame {
    private final Cars cars;
    private final NumberGenerator numberGenerator;

    public RacingGame(final Cars cars, final NumberGenerator numberGenerator) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
    }

    public void raceTimesBy(int trial) {
        while (trial-- > 0) {
            cars.race(numberGenerator);
        }
    }

    public RacingResult createRacingResult() {
        return new RacingResult(cars.getRacingCars());
    }
}
