package racingcar.controller;

import racingcar.controller.dto.NamesAndCountRequest;
import racingcar.controller.dto.ResultResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {
    private final InputView inputView;//입력
    private final OutputView outputView;//출력
    private final RacingCarService racingCarService;

    public ConsoleController(final InputView inputView, final OutputView outputView, final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void plays() {
        String names = inputView.inputCarName();
        int count = inputView.inputGameRound();
        NamesAndCountRequest namesAndCountRequest = new NamesAndCountRequest(names, count);
        ResultResponse resultResponse = racingCarService.playGame(namesAndCountRequest);
        outputView.printResultMessage();
    }
}
