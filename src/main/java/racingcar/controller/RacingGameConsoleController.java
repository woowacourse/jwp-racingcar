package racingcar.controller;

import java.util.List;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService service;

    public RacingGameConsoleController(InputView inputView, OutputView outputView, final RacingCarService service) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.service = service;
    }

    public void execute() {
        outputView.printResult(service.play(inputCarNames(), inputTrialCount()));
    }

    private List<String> inputCarNames() {
        outputView.printInputCarNamesNotice();
        return inputView.inputCarNames();
    }

    private int inputTrialCount() {
        outputView.printInputTryTimesNotice();
        return inputView.inputTrialCount();
    }
}
