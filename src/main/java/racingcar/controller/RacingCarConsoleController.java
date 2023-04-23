package racingcar.controller;

import racingcar.dto.RacingCarRequestDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {
    private final RacingCarService racingCarService;

    public RacingCarConsoleController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    public void run() {
        RacingCarRequestDto racingCarRequestDto = InputView.addRace();
        OutputView.printResult(racingCarService.addRace(racingCarRequestDto));
    }
}
