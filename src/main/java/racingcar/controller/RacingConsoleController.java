package racingcar.controller;

import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingConsoleController {

    private final RacingGameService racingGameService;

    public RacingConsoleController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void run() {
        String names = InputView.inputNames();
        int count = InputView.inputTryCount();
        RacingGameRequest racingGameRequest = new RacingGameRequest(names, count);

        RacingGameResponse racingGameResponse = racingGameService.play(racingGameRequest);

        OutputView.printRacing(racingGameResponse.getRacingCars());
        OutputView.printWinners(racingGameResponse.getWinners());
    }
}
