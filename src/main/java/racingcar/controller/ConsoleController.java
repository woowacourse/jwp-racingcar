package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingRequest;
import racingcar.service.RacingService;
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
        RacingRequest racingRequest = new RacingRequest(initializeCars(), initializeTrial());
        Cars updatedCars = RacingGame.run(racingRequest);
        printFinalResult(updatedCars);
    }

    private String initializeCars() {
        try {
            String input = inputView.getCarNames();
            Cars.initialize(input, RandomNumberGenerator.makeInstance());
            return input;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return initializeCars();
        }
    }

    private String initializeTrial() {
        try {
            String input = inputView.getTrial();
            Trial.of(Converter.convertStringToInt(input));
            return input;
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
