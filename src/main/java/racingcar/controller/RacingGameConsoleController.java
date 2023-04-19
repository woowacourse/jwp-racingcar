package racingcar.controller;

import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.Cars;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

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
            final List<String> nameValues = List.of(inputView.readCarNames().split(","));
            return new Cars(nameValues);
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
