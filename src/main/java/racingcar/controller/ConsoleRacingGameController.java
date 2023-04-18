package racingcar.controller;

import racingcar.domain.CarRandomNumberGenerator;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.response.CarGameResponse;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class ConsoleRacingGameController {

    private final RacingGame racingGame;

    public ConsoleRacingGameController() {
        List<String> carNames = InputView.inputCarNames();
        int tryCount = InputView.inputTryCount();
        racingGame = new RacingGame(new CarRandomNumberGenerator(), new Cars(carNames), tryCount);
    }

    public void run() {
        OutputView.printBeforeRacing();
        CarGameResponse carGameResult = racingGame.play();
        OutputView.printCarGameResult(carGameResult);
    }
}
