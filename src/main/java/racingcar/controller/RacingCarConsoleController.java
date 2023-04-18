package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameCount;
import racingcar.domain.PowerGenerator;
import racingcar.util.CarNamesDivider;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.*;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CarNamesDivider carNamesDivider;

    public RacingCarConsoleController() {
        inputView = new InputView(System.in);
        outputView = new OutputView();
        carNamesDivider = new CarNamesDivider();
    }

    public void run() {
        String carNames = inputView.requestCarNames();
        List<String> carNamesByDivider = carNamesDivider.divideCarNames(carNames);
        List<Car> inputCars = carNamesByDivider.stream()
                .map(Car::new)
                .collect(toList());
        Cars cars = new Cars(inputCars);
        GameCount gameCount = new GameCount(inputView.requestNumberOfTimes());
        progress(cars, gameCount);
        finish(cars);
    }

    private void progress(Cars cars, GameCount gameCount) {
        while (gameCount.isGameProgress()) {
            gameCount.proceedOnce();
            moveAllCar(cars);
        }
    }

    private void moveAllCar(Cars cars) {
        cars.moveAll(new PowerGenerator(new Random()));
    }

    private void finish(Cars cars) {
        outputView.printWinners(cars.getWinners());
        outputView.printResult(cars.getCars());
    }

}