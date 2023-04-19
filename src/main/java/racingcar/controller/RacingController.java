package racingcar.controller;

import racingcar.exception.CustomException;
import racingcar.model.car.Cars;
import racingcar.service.RacingService;
import racingcar.view.inputview.InputView;
import racingcar.view.outputview.OutputView;

public class RacingController {

    private final RacingService racingService;

    public RacingController(RacingService racingService) {
        this.racingService = racingService;
    }

    public void start() {
        Cars finishedCars = play();
        OutputView.printWinnerCars(finishedCars);
        OutputView.printCurrentCarsPosition(finishedCars);
    }

    private Cars play() {
        try {
            String carNames = InputView.inputCarNames();
            String trialTimes = InputView.inputTrialTimes();
            return racingService.play(carNames, trialTimes);
        } catch (CustomException customException) {
            terminated(customException);
            return play();
        }
    }

    public void terminated(final CustomException customException) {
        OutputView.printErrorMessage(customException);
    }
}
