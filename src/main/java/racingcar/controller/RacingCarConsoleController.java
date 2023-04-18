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
        Cars cars = new Cars(inputView.readCarNames());
        TryCount tryCount = new TryCount(inputView.readTryCount());
        playRound(cars, tryCount);
        printGameResult(cars);
    }

    private void playRound(Cars cars, TryCount tryCount) {
        for (int i = 0; i < tryCount.getValue(); i++) {
            cars.runRound();
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
