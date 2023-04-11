package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.view.input.InputView;
import racingcar.view.output.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController();
        consoleController.execute(new InputView(), new OutputView());
    }
}
