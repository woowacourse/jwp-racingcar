package racingcar.controller;

import racingcar.dto.GameInitializationRequest;
import racingcar.dto.GameResultResponse;
import racingcar.service.RacingCarGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private final RacingCarGameService racingCarGameService;

    public ConsoleRacingCarController(final RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
    }

    public void run() {
        final String names = InputView.readCarNames();
        final int attemptNumber = InputView.readAttemptNumber();

        final GameInitializationRequest gameInitializationRequest = new GameInitializationRequest(names, attemptNumber);
        final GameResultResponse gameResultResponse = racingCarGameService.raceCar(gameInitializationRequest);
        OutputView.printResult(gameResultResponse);
    }
}
