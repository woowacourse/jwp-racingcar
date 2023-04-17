package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class ConsoleApplication {

    private static InputView createInputView() {
        return new InputView(new Scanner(System.in), new OutputView());
    }

    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController(createInputView(), new OutputView());
        consoleController.run();
    }
}
