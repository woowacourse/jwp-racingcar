package racingcar.controller;

import org.springframework.stereotype.Component;
import racingcar.dto.request.CarGameRequest;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Component
public class ConsoleRacingGameController {

    private final RacingGameService racingGame;

    public ConsoleRacingGameController(RacingGameService racingGame) {
        this.racingGame = racingGame;
    }

    public void run() {
        OutputView.printBeforeRacing();

        String carNames = InputView.inputCarNames();
        int tryCount = InputView.inputTryCount();
        CarGameRequest request = new CarGameRequest(carNames, tryCount);

        OutputView.printResult(racingGame.play(request));
    }
}
