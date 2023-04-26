package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        final ConsoleController consoleController = new ConsoleController(new InputView(), new OutputView());
        consoleController.run();
    }
}
