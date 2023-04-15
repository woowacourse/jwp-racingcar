package racingcar.controller;

import racingcar.domain.car.Cars;
import racingcar.domain.strategy.NumberGenerator;
import racingcar.domain.strategy.NumberMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.function.Supplier;

public class RacingCarGameConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;
    private Cars cars;

    public RacingCarGameConsoleController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        intiateCars();
        race();
        identifyWinners();
    }

    private void intiateCars() {
        final String carNames = repeater(this::readCarNames);
        cars = Cars.createCars(carNames);
    }

    private String readCarNames() {
        return inputView.readCarNames();
    }

    private void race() {
        int count = repeater(inputView::readTryCount);
        outputView.printResultOpening();

        while (count-- > 0) {
            cars.moveCars(new NumberMovingStrategy(numberGenerator));
        }
    }

    private void identifyWinners() {
        outputView.printWinners(cars.getWinningCars());
    }

    private <T> T repeater(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeater(supplier);
        }
    }
}
