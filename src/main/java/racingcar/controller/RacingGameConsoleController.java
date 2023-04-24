package racingcar.controller;

import java.io.IOException;

import racingcar.controller.dto.ConsoleRacingResultDto;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.CarGroup;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingGameConsoleController(final InputView inputView, final OutputView outputView, final RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }

    public void run() throws IOException {
        final String carNames = inputView.readCarNames();
        final CarGroup carGroup = new CarGroup(carNames);
        final int trial = inputView.readMovingTrial();

        final RacingGameResponse racingGameResponse = racingGameService.race(carGroup, trial);
        final ConsoleRacingResultDto consoleRacingResultDto = new ConsoleRacingResultDto(racingGameResponse);
        outputView.printNotice();
        outputView.printWinner(consoleRacingResultDto.getNames());
        outputView.printRacingResult(consoleRacingResultDto.getHistory());
    }
}
