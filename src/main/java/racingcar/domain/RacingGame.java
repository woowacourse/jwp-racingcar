package racingcar.domain;

import racingcar.utils.RacingNumberGenerator;
import racingcar.utils.RacingRandomNumberGenerator;

import java.util.List;

public class RacingGame {

    private final Cars cars;
    private final Round round;
    private final RacingNumberGenerator generator;

    private RacingGame(final Cars cars, final Round round) {
        this.cars = cars;
        this.round = round;
        this.generator = new RacingRandomNumberGenerator();
    }

    public static RacingGame of(final List<String> carNames, final String count) {
        final Cars cars = Cars.from(carNames);
        final Round round = Round.from(count);

        return new RacingGame(cars, round);
    }

    public void playGame() {
        final int round = this.round.getRound();
        for (int count = 0; count < round; count++) {
            cars.race(generator);
        }
    }

    public String findWinnerNames() {
        return cars.findWinnerNames();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

}
