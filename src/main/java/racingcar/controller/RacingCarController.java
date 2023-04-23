package racingcar.controller;

import racingcar.domain.RacingCars;
import racingcar.util.NumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    private final OutputView outputView;
    private final InputView inputView;
    private final NumberGenerator numberGenerator;

    public RacingCarController(final NumberGenerator numberGenerator) {
        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        final String carNames = inputView.readCarNames();
        int trialCount = inputView.readTryNum();

        RacingCars racingCars = moveCars(carNames, trialCount);
        showRaceResult(racingCars);
    }

    private void showRaceResult(final RacingCars racingCars) {
        showWinners(racingCars);
    }

    private RacingCars moveCars(String carNames, int trialCount) {
        RacingCars racingCars = RacingCars.makeCars(carNames);
        racingCars.moveAllCars(trialCount, numberGenerator);
        return racingCars;
    }

    private void showWinners(RacingCars racingCars) {
        outputView.printWinners(racingCars);
    }
}
