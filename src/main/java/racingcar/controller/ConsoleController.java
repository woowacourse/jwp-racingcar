package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.utils.Converter;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.domain.vo.Trial;


public class ConsoleController {

    private final OutputView outputView;
    private final InputView inputView;

    public ConsoleController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        Cars cars = initializeCars();
        Trial trial = initializeTrial();
        Cars movedCars = playGame(cars, trial);
        printFinalResult(movedCars);
    }

    private Cars initializeCars() {
        try {
            return Cars.initialize(inputView.getCarNames(), RandomNumberGenerator.makeInstance());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return initializeCars();
        }
    }

    private Trial initializeTrial() {
        try {
            return Trial.of(Converter.convertStringToInt(inputView.getTrial()));
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return initializeTrial();
        }
    }

    private Cars playGame(Cars cars, Trial trial) {
        outputView.printResultMessage();
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
            printResult(cars);
        }
        return cars;
    }

    private void printFinalResult(Cars cars) {
        printResult(cars);
        outputView.printWinners(cars.getWinnerNames());
    }

    private void printResult(Cars cars) {
        outputView.printResult(cars.getRacingCars());
    }
}
