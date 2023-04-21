package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private static final int MOVABLE_BOUND = 4;

    private final List<Car> cars;
    private final NumberGenerator numberGenerator;
    private final Coin gameCoin;

    public RacingGame(final List<Car> cars, final int gameTry, final NumberGenerator numberGenerator) {
        this.cars = cars;
        this.gameCoin = new Coin(gameTry);
        this.numberGenerator = numberGenerator;
    }

    public void start() {
        while (isGameOnGoing()) {
            judgeMoveOrNot();
        }
    }

    private void judgeMoveOrNot() {
        for (Car car : cars) {
            moveCar(car);
        }
        gameCoin.use();
    }

    private boolean isGameOnGoing() {
        return gameCoin.isLeft();
    }

    private void moveCar(final Car car) {
        int randomNumber = numberGenerator.makeDigit();
        if (randomNumber >= MOVABLE_BOUND) {
            car.move();
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public List<Car> getWinners() {
        Car furthestCar = getFurthestCar();

        return cars.stream()
                .filter(car -> car.hasSamePositionWith(furthestCar))
                .collect(Collectors.toList());
    }

    private Car getFurthestCar() {
        return cars.stream()
                .max(Car::comparePosition)
                .orElseThrow(() -> new IllegalArgumentException("자동차를 찾을 수 없습니다."));
    }

    public Coin getGameCoin() {
        return gameCoin;
    }
}
