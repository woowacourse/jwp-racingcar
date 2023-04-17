package racingcar.controller;

import racingcar.dto.GameDto;
import racingcar.service.CarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleCarController {
    private final CarService carService;

    public ConsoleCarController(final CarService carService) {
        this.carService = carService;
    }

    public void run() {
        try {
            final String names = InputView.inputCarsName();
            final String round = InputView.inputRound();

            OutputView.printResult(carService.playGame(new GameDto(names, round)));
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            run();
        }
    }
}