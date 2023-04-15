package racingcar.domain;

public class RacingGame {

    private final Cars cars;
    private final Lap lap;

    public RacingGame(Cars cars, int lap) {
        this.cars = cars;
        this.lap = new Lap(lap);
    }

    public void race(NumberGenerator numberGenerator) {
        while (lap.isPlayable()) {
            cars.moveCars(numberGenerator);
            lap.reduce();
        }
    }
}
