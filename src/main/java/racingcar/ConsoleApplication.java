package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController(
                new InputView(),
                new OutputView(),
                new RandomNumberGenerator()
        );
        consoleController.newCarNames();
        consoleController.newGameRound();
        consoleController.play();
    }
}
