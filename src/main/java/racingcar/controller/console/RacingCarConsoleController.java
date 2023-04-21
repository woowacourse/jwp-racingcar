package racingcar.controller.console;

import java.util.List;
import racingcar.dto.RacingGameDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingCarConsoleController(InputView inputView, OutputView outputView, RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }

    public void run() {
        try {
            List<String> carNames = inputView.readCarNames();
            int trialCount = inputView.readTryTime();
            RacingGameDto racingGameDto = racingGameService.play(trialCount, carNames);
            outputView.printGameResult(racingGameDto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
