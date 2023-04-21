package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.RacingCarGameResultDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController(InputView inputView, OutputView outputView, RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void run() {
        Cars cars = Cars.from(inputView.readCarNames());
        TryCount tryCount = new TryCount(inputView.readTryCount());

        RacingCarGameResultDto racingCarGameResultDto = racingCarService.play(cars, tryCount);

        outputView.printGameResult(racingCarGameResultDto);
    }
}
