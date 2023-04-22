package racingcar.controller;

import racingcar.dto.response.RacingGameResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController(final InputView inputView, final OutputView outputView, final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void run() {
        final RacingGameResponse racingGameResponse = playRacingGame();
        outputView.printRacingGameResult(racingGameResponse);
    }

    private RacingGameResponse playRacingGame() {
        while (true) {
            try {
                return racingCarService.play(inputView.readCarNames(), inputView.readCount());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
