package racingcar.controller;

import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.service.WebRacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private final WebRacingCarService webRacingCarService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleRacingCarController(final WebRacingCarService webRacingCarService,
                                      final InputView inputView,
                                      final OutputView outputView) {
        this.webRacingCarService = webRacingCarService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final String names = inputView.readCarNames();
        int round = inputView.readRacingRound();
        final RacingGameRequest racingGameRequest = new RacingGameRequest(names, round);
        final RacingGameResponse racingGameResponse = webRacingCarService.play(racingGameRequest);
        outputView.printResultMessage(racingGameResponse);
    }
}
