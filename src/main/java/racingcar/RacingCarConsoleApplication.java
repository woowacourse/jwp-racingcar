package racingcar;

import racingcar.controller.console.RacingGameConsoleController;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGameConsoleController racingCarController = new RacingGameConsoleController();
        racingCarController.run();
    }
}
