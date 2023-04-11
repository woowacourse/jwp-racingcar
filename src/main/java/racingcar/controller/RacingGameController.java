package racingcar.controller;

import racingcar.domain.CarRandomNumberGenerator;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingGameController {

    private final RacingGame racingGame;

    public RacingGameController() {
        List<String> carNames = InputView.inputCarNames();
        int tryCount = InputView.inputTryCount();
        racingGame = new RacingGame(new CarRandomNumberGenerator(), new Cars(carNames), tryCount);
    }

    public void run() {
        OutputView.printBeforeRacing();
        while (!racingGame.isEnd()) {
            racingGame.play();
            Cars cars = racingGame.getCars();
            OutputView.printRacing(cars.getUnmodifiableCars());
        }
        OutputView.printWinners(racingGame.decideWinners());
    }
}
