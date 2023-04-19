package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.dto.RacingRequest;
import racingcar.service.ConsoleService;
import racingcar.utils.Converter;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.domain.vo.Trial;


public class ConsoleController {

    private final OutputView outputView;
    private final InputView inputView;
    final private ConsoleService service;

    public ConsoleController(OutputView outputView, InputView inputView, ConsoleService service) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.service=service;
    }

    public void run() {
        Cars cars = initializeCars();
        Trial trial = initializeTrial();
        Cars movedCars = service.run(cars, trial);
        printFinalResult(movedCars);
    }

    private Cars initializeCars() {
        try {
            String input = inputView.getCarNames();
            return Cars.initialize(input, RandomNumberGenerator.makeInstance());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return initializeCars();
        }
    }

    private Trial initializeTrial() {
        try {
            String input = inputView.getTrial();
            return Trial.of(Converter.convertStringToInt(input));
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return initializeTrial();
        }
    }

    private void printFinalResult(Cars cars) {
        printResult(cars);
        outputView.printWinners(cars.getWinnerNames());
    }

    private void printResult(Cars cars) {
        outputView.printResult(cars);
    }
}
