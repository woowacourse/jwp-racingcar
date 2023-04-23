package racingcar.controller;

import java.util.List;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;
import racingcar.service.RacingGameAddService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingConsoleController {

    private final RacingGameAddService racingGameAddService;

    public RacingConsoleController(RacingGameAddService racingGameAddService) {
        this.racingGameAddService = racingGameAddService;
    }

    public void run() {
        List<String> names = InputView.inputNames();
        int count = InputView.inputTryCount();
        RacingGameRequest racingGameRequest = new RacingGameRequest(names, count);

        RacingGameResponse racingGameResponse = racingGameAddService.play(racingGameRequest);

        OutputView.printRacing(racingGameResponse.getRacingCars());
        OutputView.printWinners(racingGameResponse.getWinners());
    }
}
