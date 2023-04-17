package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.exception.NoCarsExistException;

public class RacingGame {

    private static final int DEFAULT_START_LINE = 0;
    private static final int MOVABLE_BOUND = 4;

    private final List<Car> cars;
    private final NumberGenerator numberGenerator;
    private final Coin gameCoin;

    public RacingGame(List<String> splitCarNames, int gameTry, NumberGenerator numberGenerator) {
        this.cars = splitCarNames.stream()
                .map(carName -> new Car(carName, DEFAULT_START_LINE))
                .collect(Collectors.toList());
        this.gameCoin = new Coin(gameTry);
        this.numberGenerator = numberGenerator;
    }

    public void start() {
        for (Car car : this.cars) {
            moveCar(car);
        }
        this.gameCoin.use();
    }

    private void moveCar(Car car) {
        int randomNumber = numberGenerator.makeDigit();
        if (randomNumber >= MOVABLE_BOUND) {
            car.move();
        }
    }

    public boolean isGameOnGoing() {
        return gameCoin.isLeft();
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(this.cars);
    }

    public List<Car> getWinners() {
        Car furthestCar = getFurthestCar();

        return this.cars.stream()
                .filter(car -> car.hasSamePositionWith(furthestCar))
                .collect(Collectors.toList());
    }

    private Car getFurthestCar() {
        return this.cars.stream()
                .max(Car::comparePosition)
                .orElseThrow(NoCarsExistException::new);
    }
}
