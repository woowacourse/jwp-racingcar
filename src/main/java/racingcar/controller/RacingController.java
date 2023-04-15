package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.TrialCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private final RacingGame racingGame = new RacingGame();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Cars cars = racingGame.createCars(inputView.readCarNames());
        TrialCount trialCount = racingGame.createTrialCount(inputView.readTryCount());

        racingGame.playRacing(cars, trialCount);

        outputView.printResult(cars.getCars(), cars.winnerNames());
    }
}
