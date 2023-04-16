package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {

    public static void main(String[] args) {
        final ConsoleController racingGame = new ConsoleController(
                new InputView(),
                new OutputView(),
                new RacingGameService());
        racingGame.play();
    }
}
