package racing.console;

import racing.console.controller.RacingCarConsoleController;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController();

        racingCarConsoleController.playGame();
    }
}
