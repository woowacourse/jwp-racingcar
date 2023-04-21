package racingcar.controller;

import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.ResultResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private final RacingCarService racingCarService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleController(final RacingCarService racingCarService, final InputView inputView,
                             final OutputView outputView) {
        this.racingCarService = racingCarService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final NamesAndCountRequest namesAndCountRequest = new NamesAndCountRequest(
                String.join(",", inputView.inputCarName()), inputView.inputGameRound());
        final ResultResponse resultResponse = racingCarService.playGame(namesAndCountRequest);
        outputView.printEndGameResult(resultResponse);
    }
}
