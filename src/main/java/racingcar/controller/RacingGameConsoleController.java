package racingcar.controller;

import racingcar.dto.OneGameHistoryDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingGameConsoleController {

    private final RacingGameService racingGameService;

    public RacingGameConsoleController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void start() {
        OneGameHistoryDto oneGameHistoryDto = racingGameService.run(inputCarNames(), requestTryCount());
        OutputView.printWinner(oneGameHistoryDto);
    }

    private List<String> inputCarNames() {
        return InputView.requestCarName();
    }

    private int requestTryCount() {
        return InputView.requestTryCount();
    }
}
