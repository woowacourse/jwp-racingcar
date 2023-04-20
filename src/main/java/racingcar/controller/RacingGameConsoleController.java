package racingcar.controller;

import racingcar.service.RacingGameService;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.ResultDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingGameConsoleController(RacingGameService racingGameService) {
        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.racingGameService = racingGameService;
    }

    public void run() {
        RacingGameRequest racingGameRequest = readGameRequest();
        ResultDto resultDto = racingGameService.start(racingGameRequest.getCount(), racingGameRequest.convertToSplitedNames());
        outputView.printResult(resultDto);
    }

    private RacingGameRequest readGameRequest() {
        while (true) {
            try {
                return inputView.readGameRequest();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

}
