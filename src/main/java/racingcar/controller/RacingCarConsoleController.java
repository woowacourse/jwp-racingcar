package racingcar.controller;

import java.util.List;
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

        racingCarService.playRound(cars, tryCount);
        racingCarService.saveGameResult(cars, tryCount);

        List<RacingCarGameResultDto> gameResult = racingCarService.getGameResult();
        outputView.printAllGameResult(gameResult);
    }
}
