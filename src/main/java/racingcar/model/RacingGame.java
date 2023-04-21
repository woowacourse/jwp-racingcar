package racingcar.model;

import java.util.stream.Collectors;

import racingcar.Strategy.NumberGenerator;

public class RacingGame {
    private final Cars cars;
    private final Trial trial;
    private final NumberGenerator numberGenerator;

    public RacingGame(Cars cars, Trial trial, NumberGenerator numberGenerator) {
        this.cars = cars;
        this.trial = trial;
        this.numberGenerator = numberGenerator;
    }

    public void play() {
        cars.move(trial.getTrial(), numberGenerator);
    }

    public String winners() {
        return cars.findWinners()
            .stream()
            .map(Car::getName)
            .collect(Collectors.joining(", "));
    }
}
