package racingcar.controller;

import racingcar.dto.RacingResultDTO;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public ConsoleController(final InputView inputView, final OutputView outputView, final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void play() {
        final RacingResultDTO racingResult = racingCarService.play(inputView.readCarNames(), inputView.readGameRound());
        outputView.printRacingResult(racingResult.getWinners(), racingResult.getRacingCars());
    }
}
