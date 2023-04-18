package racingcar;

import racingcar.controller.ConsoleController;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        ConsoleController gameController = new ConsoleController();
        gameController.run();
    }
}
