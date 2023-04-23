package racingcar.controller;

import racingcar.dto.ResultDto;
import racingcar.dto.UserInputDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingGameConsoleController(InputView inputView, OutputView outputView, RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }

    public void run() {
        String carNames = inputView.readCarNames();
        int tryCount = inputView.readTryCount();
        Long resultId = racingGameService.addGameResultAndCars(new UserInputDto(carNames, tryCount));
        ResultDto resultDto = racingGameService.findResults(resultId);
        outputView.printWinners(resultDto);
    }
}
