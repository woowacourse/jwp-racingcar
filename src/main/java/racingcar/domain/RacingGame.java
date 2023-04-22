package racingcar.domain;

import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.util.CarNamesDivider;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RacingGame {

    private static RacingGame racingGame;

    private final Cars cars;
    private final GameCount gameCount;

    private RacingGame(Cars cars, GameCount gameCount) {
        this.cars = cars;
        this.gameCount = gameCount;
    }

    public static RacingGame from(GameRequestDtoForPlays gameRequestDtoForPlays) {
        racingGame = new RacingGame(generateCars(gameRequestDtoForPlays), new GameCount(gameRequestDtoForPlays.getCount()));
        return racingGame;
    }

    private static Cars generateCars(GameRequestDtoForPlays gameRequestDtoForPlays) {
        CarNamesDivider carNamesDivider = new CarNamesDivider();
        List<String> carNamesByDivider = carNamesDivider.divideCarNames(gameRequestDtoForPlays.getNames());
        List<Car> inputCars = carNamesByDivider.stream()
                .map(Car::new)
                .collect(toList());
        return new Cars(inputCars);
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

    public int getCount() {
        return gameCount.getGameCount();
    }

    public Cars getCars() {
        return cars;
    }
}
