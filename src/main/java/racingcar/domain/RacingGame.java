package racingcar.domain;

import java.util.List;

import racingcar.utils.NumberGenerator;

public class RacingGame {
    private final NumberGenerator randomNumberGenerator;
    private final Winner winner;
    private final List<Car> cars;
    private final int tryCount;

    public RacingGame(List<Car> cars, int tryCount, NumberGenerator randomNumberGenerator) {
        winner = new Winner();
        this.cars = cars;
        this.tryCount = tryCount;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void run() {
        TryCount tryCount = new TryCount(this.tryCount);
        while (tryCount.hasNext()) {
            cars.forEach(car -> car.move(randomNumberGenerator.generateNumber()));
            tryCount = tryCount.next();
        }
        winner.makeWinnerNames(cars);
    }

    public Winner getWinner() {
        return winner;
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getTryCount() {
        return tryCount;
    }
}
