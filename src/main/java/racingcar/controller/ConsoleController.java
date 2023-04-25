package racingcar.controller;

import racingcar.controller.dto.GameStartRequest;
import racingcar.controller.dto.GameResultReponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public ConsoleController(final InputView inputView, final OutputView outputView, final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void plays() {
        String names = inputView.inputCarName();
        int count = inputView.inputGameRound();
        GameStartRequest gameStartRequest = new GameStartRequest(names, count);
        GameResultReponse gameResultReponse = racingCarService.playGame(gameStartRequest);
        outputView.printRoundResult(gameResultReponse);
    }
}
