package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController();
        consoleController.execute(new InputView(), new OutputView());
    }
}
