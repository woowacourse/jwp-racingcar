package racingcar.controller;

import racingcar.controller.dto.RacingGameResponse;
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
        final RacingGameResponse racingGameResponse = racingGameService.race(readCarNames(), readTrial());
        outputView.printRacingResult(racingGameResponse);
    }

    private List<String> readCarNames() {
        try {
            return List.of(inputView.readCarNames().split(","));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readCarNames();
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
