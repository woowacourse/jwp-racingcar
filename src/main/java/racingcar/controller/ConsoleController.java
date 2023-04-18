package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.TrialCount;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private RacingGameService racingGameService;

    public ConsoleController() {
        this.racingGameService = new RacingGameService();
    }

    public void run() {
        String carNames = inputView.inputCarNames();
        Cars cars = Cars.of(carNames);
        TrialCount trialCount = TrialCount.of(inputView.inputTrialCount());
        racingGameService.playMultipleTimes(cars, trialCount);
        showResults(cars);
    }

    private void showResults(Cars cars) {
        outputView.noticeResult();
        outputView.printCars(racingGameService.getCars(cars));
        outputView.printWinners(racingGameService.getWinners(cars));
    }
}
