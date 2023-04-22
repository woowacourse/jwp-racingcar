package racingcar.domain;

import racingcar.util.NumberGenerator;

public class RacingCarGame {

    private final Cars cars;
    private final Count count;

    public RacingCarGame(final Cars cars, final Count count) {
        this.cars = cars;
        this.count = count;
    }

    public void play (NumberGenerator numberGenerator) {
        for (int i = 0; i < count.getCount(); i++) {
            cars.moveAll(numberGenerator);
        }
    }

    public Cars findWinners() {
        return cars.pickWinners();
    }

    public int findWinnerPosition() {
       return findWinners().getAll().stream()
           .map(Car::getPosition)
           .findFirst()
           .orElseThrow();
    }

    public Cars getCars() {
        return cars;
    }

    public int getCount() {
        return count.getCount();
    }
}
