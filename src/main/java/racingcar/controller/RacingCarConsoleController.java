package racingcar.controller;

import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController(final InputView inputView,
                                      final OutputView outputView,
                                      final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void run() {
        final String carNames = inputView.askCarNames();
        final int racingCount = inputView.askRacingCount();

        final PlayResultDto result = racingCarService.play(new PlayRequestDto(carNames, racingCount));

        outputView.printPlayResult(result);
    }
}
