package racingcar.controller;

import java.util.List;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;
import racingcar.service.RacingGamePlayService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {
    private final RacingGamePlayService racingPlayService;

    public RacingGameConsoleController(RacingGamePlayService racingPlayService) {
        this.racingPlayService = racingPlayService;
    }

    public void run() {
        List<String> names = InputView.inputNames();
        int count = InputView.inputTryCount();

        RacingGameResponse racingGameResponse = racingPlayService.play(new RacingGameRequest(names, count));

        OutputView.printRacing(racingGameResponse.getRacingCars());
        OutputView.printWinners(racingGameResponse.getWinners());
    }
}
