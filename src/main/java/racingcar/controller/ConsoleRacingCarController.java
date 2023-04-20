package racingcar.controller;

import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private final RacingCarService racingCarService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleRacingCarController(final RacingCarService racingCarService,
                                      final InputView inputView,
                                      final OutputView outputView) {
        this.racingCarService = racingCarService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final String names = inputView.readCarNames();
        int round = inputView.readRacingRound();
        final RacingGameRequest racingGameRequest = new RacingGameRequest(names, round);
        final RacingGameResponse racingGameResponse = racingCarService.play(racingGameRequest);
        outputView.printResultMessage(racingGameResponse);
    }
}
