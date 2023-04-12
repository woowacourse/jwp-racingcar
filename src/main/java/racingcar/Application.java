package racingcar;

import racingcar.controller.RacingGameConsoleController;

public class Application {

    public static void main(String[] args) {
        RacingGameConsoleController racingGameController = new RacingGameConsoleController();
        racingGameController.run();
    }
}
