package racingcar;

import racingcar.controller.RacingCarConsoleController;

public class Application {

    public static void main(final String[] args) {
        final RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController();
        racingCarConsoleController.run();
    }
}
