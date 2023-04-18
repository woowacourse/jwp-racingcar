package racingcar.controller;

import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.Cars;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingGameConsoleController(final RacingGameService racingGameService) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.racingGameService = racingGameService;
    }

    public void run() {
        final RacingGameResponse racingGameResponse = racingGameService.race(readCars(), readTrial());
        outputView.printRacingResult(racingGameResponse);
    }

    private Cars readCars() {
        try {
            return new Cars(inputView.readCarNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readCars();
        }
    }

    private int readTrial() {
        try {
            return inputView.readMovingTrial();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readTrial();
        }
    }
}
