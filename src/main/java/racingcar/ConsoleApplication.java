package racingcar;

import java.util.Scanner;

import racingcar.controller.ConsoleController;
import racingcar.ui.View;

public class ConsoleApplication {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            View view = new View(scanner);
            ConsoleController consoleController = new ConsoleController(view);
            consoleController.run();
        }
    }
}
