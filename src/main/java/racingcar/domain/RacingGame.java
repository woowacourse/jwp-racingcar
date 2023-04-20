package racingcar.domain;

import racingcar.domain.exception.NoCarsExistException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private static final int DEFAULT_START_LINE = 0;
    private static final int MOVABLE_BOUND = 4;

    private final List<Car> cars;
    private final NumberGenerator numberGenerator;
    private final Coin gameCoin;

    public RacingGame(List<String> splitCarNames, int gameTry, NumberGenerator numberGenerator) {
        cars = splitCarNames.stream()
                .map(carName -> new Car(carName, DEFAULT_START_LINE))
                .collect(Collectors.toList());
        gameCoin = new Coin(gameTry);
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

    private void moveCar(Car car) {
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

    public Coin getGameCoin() {
        return gameCoin;
    }

    private Car getFurthestCar() {
        return cars.stream()
                .max(Car::comparePosition)
                .orElseThrow(NoCarsExistException::new);
    }
}
