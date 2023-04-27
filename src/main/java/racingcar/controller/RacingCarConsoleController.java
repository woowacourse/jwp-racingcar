package racingcar.controller;

import java.util.List;
import racingcar.dto.GameInitializeDto;
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
        List<String> names = inputView.readCarNames();
        int tryCount = inputView.readTryCount();
        
        RacingCarGameResultDto racingCarGameResultDto = racingCarService.play(new GameInitializeDto(names, tryCount));

        outputView.printGameResult(racingCarGameResultDto);
    }
}
