package racingcar;

import racingcar.controller.RacingCarGameConsoleController;
import racingcar.domain.strategy.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        RacingCarGameConsoleController consoleController = new RacingCarGameConsoleController(new InputView(),
                new OutputView(),
                new RandomNumberGenerator());

        consoleController.run();
    }
}
