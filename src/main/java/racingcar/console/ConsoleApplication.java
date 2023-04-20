package racingcar.console;

import racingcar.console.controller.ConsoleController;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController();
        consoleController.runGame();
    }
}
