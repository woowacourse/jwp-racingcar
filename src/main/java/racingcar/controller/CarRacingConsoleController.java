package racingcar.controller;

import racingcar.service.CarRacingService;
import racingcar.service.dto.RacingResultDTO;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class CarRacingConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CarRacingService carRacingService;

    public CarRacingConsoleController(final InputView inputView, final OutputView outputView, final CarRacingService carRacingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.carRacingService = carRacingService;
    }

    public void play() {
        final RacingResultDTO racingResult = carRacingService.play(inputView.readCarNames(), inputView.readGameRound());
        outputView.printRacingResult(racingResult.getWinners(), racingResult.getRacingCars());
    }
}
