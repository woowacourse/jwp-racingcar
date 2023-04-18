package racingcar.controller;

import org.springframework.stereotype.Component;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Component
public class RacingConsoleController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final RacingGameService racingGameService;

    private RacingConsoleController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void run() {
        try {
            play();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            play();
        }

    }

    private void play() {
        final String tryCount = inputView.readTryCount();
        final GameResponseDto responseDto = racingGameService.play(inputView.readCarName(),
                Integer.parseInt(tryCount));
        outputView.printResult(responseDto);
    }
}
