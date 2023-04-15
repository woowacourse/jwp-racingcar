package racingcar.controller;

import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.domain.strategy.NumberMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RacingCarGameConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MovingStrategy movingStrategy;

    public RacingCarGameConsoleController(InputView inputView, OutputView outputView, NumberMovingStrategy movingStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.movingStrategy = movingStrategy;
    }

    public void run() {
        final Cars cars = createCars();
        race(cars);
        printResult(cars);
    }

    private Cars createCars() {
        final String carNames = repeatUntilNoIAE(inputView::readCarNames);
        return Cars.createCars(carNames);
    }

    private void race(final Cars cars) {
        int count = repeatUntilNoIAE(inputView::readTryCount);

        while (count-- > 0) {
            cars.moveCars(movingStrategy);
        }
    }

    private void printResult(final Cars cars) {
        final Map<String, Integer> raceResult = cars.getCars()
                                                    .stream()
                                                    .collect(Collectors.toUnmodifiableMap(Car::getName, Car::getPosition));
        final List<String> winners = cars.getWinningCars()
                                         .stream()
                                         .map(Car::getName)
                                         .collect(Collectors.toUnmodifiableList());

        outputView.printResultOpeningMessage();
        outputView.printRaceResult(raceResult);
        outputView.printWinners(winners);
    }

    private <T> T repeatUntilNoIAE(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeatUntilNoIAE(supplier);
        }
    }
}
