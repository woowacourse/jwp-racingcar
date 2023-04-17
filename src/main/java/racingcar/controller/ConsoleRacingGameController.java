package racingcar.controller;

import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingGameController {
    private final RacingGameService racingGameService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleRacingGameController(
            final RacingGameService racingGameService,
            final InputView inputView,
            final OutputView outputView
    ) {
        this.racingGameService = racingGameService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            final GameRequestDto request = inputView.readGameRequest();
            final GameResponseDto response = racingGameService.play(request);
            outputView.printResult(response);
        } catch (final IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }
}
