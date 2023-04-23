package racingcar.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.RacingRequest;

public class RacingGame {

    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR = new RandomNumberGenerator();
    private static final int DEFAULT_START_LINE = 0;
    private static final int MOVABLE_BOUND = 4;

    private final List<Car> cars;
    private final NumberGenerator numberGenerator;
    private final Coin gameCoin;

    public RacingGame(List<Car> cars, NumberGenerator numberGenerator, Coin gameCoin) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
        this.gameCoin = gameCoin;
    }

    public static RacingGame from(RacingRequest racingRequest) {
        List<String> carNames = List.of(racingRequest.getNames().split(","));
        List<Car> cars = carNames.stream()
                .map(carName -> new Car(carName, DEFAULT_START_LINE))
                .collect(Collectors.toList());
        return new RacingGame(cars, RANDOM_NUMBER_GENERATOR, new Coin(racingRequest.getCount()));
    }

    public void play() {
        while (gameCoin.isLeft()) {
            moveEachCar();
            this.gameCoin.use();
        }
        this.order();
    }

    private void moveEachCar() {
        for (Car car : this.cars) {
            moveCar(car);
        }
    }

    private void moveCar(Car car) {
        int randomNumber = numberGenerator.makeDigit();
        if (randomNumber >= MOVABLE_BOUND) {
            car.move();
        }
    }

    private void order() {
        this.cars.sort(Comparator.comparing(Car::getPosition).reversed());
    }

    public List<Car> getCars() {
        return this.cars;
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
                .orElseThrow(() -> new IllegalStateException("자동차가 존재하지 않습니다."));
    }
}
