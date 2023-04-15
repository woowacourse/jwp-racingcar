package racingcar.domain;

import java.util.Collections;
import java.util.List;

public class GameManager {
    private final NumberGenerator numberGenerator;
    private final Cars cars;
    private final GameRound gameRound;

    public GameManager(final NumberGenerator numberGenerator, final Cars cars, final GameRound gameRound) {
        this.numberGenerator = numberGenerator;
        this.cars = cars;
        this.gameRound = gameRound;
    }

    public void playGameRound() {
        List<Car> currentCars = cars.getCars();
        for (Car car : currentCars) {
            car.move(numberGenerator.generateNumber());
        }
        gameRound.increaseRound();
    }

    public boolean isEnd() {
        return gameRound.isEnd();
    }

    public List<Car> getAllCars() {
        return Collections.unmodifiableList(cars.getCars());
    }

    public List<Car> getWinnerCars() {
        return Collections.unmodifiableList(cars.findWinner());
    }
}
