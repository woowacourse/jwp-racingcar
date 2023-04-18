package racingcar.controller.console;

import racingcar.controller.TrackResponse;
import racingcar.service.RacingService;
import racingcar.view.inputview.InputView;
import racingcar.view.outputview.OutputView;

public class RacingConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingService racingService;

    public RacingConsoleController(final InputView inputView, final OutputView outputView, final RacingService racingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingService = racingService;
    }

    public void start() {
        String names = inputView.inputCarNames();
        String trialTimes = inputView.inputTrialTimes();
        TrackResponse trackResponse = racingService.play(names, trialTimes);

        outputView.printWinnerCars(trackResponse.getWinners());
        outputView.printCurrentCarsPosition(trackResponse.getRacingCars());
    }
}
