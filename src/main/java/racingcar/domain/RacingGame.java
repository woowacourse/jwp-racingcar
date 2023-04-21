package racingcar.domain;

import java.util.Random;
import java.util.stream.Collectors;

public class RacingGame {

    private final Cars cars;
    private final GameCount gameCount;

    public RacingGame(Cars cars, GameCount gameCount) {
        this.cars = cars;
        this.gameCount = gameCount;
    }

    public void play() {
        while (gameCount.isGameProgress()) {
            gameCount.proceedOnce();
            moveAllCar(cars);
        }
    }

    private void moveAllCar(Cars cars) {
        cars.moveAll(new PowerGenerator(new Random()));
    }

    public Winners getWinners() {
        return new Winners(cars.getWinners().stream()
                .map(car -> new Winner(car.getName()))
                .collect(Collectors.toUnmodifiableList())
        );
    }

}
