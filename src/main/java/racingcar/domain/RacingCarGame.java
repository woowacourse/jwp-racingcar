package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RacingCarGame {

    private final Cars cars;
    private final AttemptNumber attemptNumber;
    private final NumberGenerator numberGenerator;

    public RacingCarGame(final Cars cars, final AttemptNumber attemptNumber, final NumberGenerator numberGenerator) {
        this.cars = cars;
        this.attemptNumber = attemptNumber;
        this.numberGenerator = numberGenerator;
    }

    public RacingCarGame(final List<String> names, final int attempt, final NumberGenerator numberGenerator) {
        this.cars = Cars.from(names);
        this.attemptNumber = new AttemptNumber(attempt);
        this.numberGenerator = numberGenerator;
    }

    public List<String> findWinners() {
        play();
        final Cars winners = cars.findWinners();
        return getWinnerNames(winners);
    }

    private void play() {
        while (attemptNumber.isRemain()) {
            attemptNumber.decrease();
            cars.moveAll(numberGenerator);
        }
    }

    private List<String> getWinnerNames(final Cars winners) {
        return winners.getCars().stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    public AttemptNumber getAttemptNumber() {
        return attemptNumber;
    }
}
