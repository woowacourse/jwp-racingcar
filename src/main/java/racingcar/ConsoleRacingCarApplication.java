package racingcar;

import racingcar.controller.console.ConsoleRacingGameController;

public class ConsoleRacingCarApplication {
    public static void main(String[] args) {
        ConsoleRacingGameController consoleRacingGameController = new ConsoleRacingGameController();
        consoleRacingGameController.playGame();
    }
}
