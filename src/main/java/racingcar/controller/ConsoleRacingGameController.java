package racingcar.controller;

import racingcar.domain.CarRandomNumberGenerator;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.response.CarGameResponse;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingGameController {

    private final RacingGame racingGame;

    public ConsoleRacingGameController() {
        String carNames = InputView.inputCarNames();
        int tryCount = InputView.inputTryCount();
        racingGame = new RacingGame(new CarRandomNumberGenerator(), Cars.from(carNames), tryCount);
    }

    public void run() {
        OutputView.printBeforeRacing();
        racingGame.play();
        CarGameResponse carGameResult = racingGame.getCarGameResult();
        OutputView.printCarGameResult(carGameResult);
    }
}
