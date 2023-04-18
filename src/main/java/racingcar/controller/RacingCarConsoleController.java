package racingcar.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {
    private final InputView inputView;
    private final OutputView outputView;

    public RacingCarConsoleController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = makeCars();
        TryCount tryCount = makeTryCount();
        outputView.printResultMessage();
        playRound(cars, tryCount);
        printGameResult(cars);
    }

    private void playRound(Cars cars, TryCount tryCount) {
        for (int i = 0; i < tryCount.getValue(); i++) {
            cars.runRound();
        }
    }

    private Cars makeCars() {
        try {
            outputView.printNameInput();
            return new Cars(inputView.readCarNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeCars();
        }
    }

    private TryCount makeTryCount() {
        try {
            outputView.printCountInput();
            return new TryCount(inputView.readTryCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeTryCount();
        }
    }

    private void printGameResult(Cars cars) {
        outputView.printWinners(cars.getWinner());
        Map<String, Integer> racingCars = new LinkedHashMap<>();
        List<Car> cars1 = cars.getCars();
        for (Car car : cars1) {
            racingCars.put(car.getName().getValue(), car.getDistance().getValue());
        }
        outputView.printGameResult(racingCars);
    }
}
