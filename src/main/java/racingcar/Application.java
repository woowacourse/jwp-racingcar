package racingcar;

import racingcar.controller.ConsoleRacingCarController;

public class Application {
    public static void main(String[] args) {
        ConsoleRacingCarController consoleRacingCarController = new ConsoleRacingCarController();
        consoleRacingCarController.run();
    }
}
