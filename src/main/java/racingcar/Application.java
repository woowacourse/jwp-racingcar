package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.service.RacingGameService;

public class Application {

    public static void main(String[] args) {
        RacingGameConsoleController racingGameController = new RacingGameConsoleController(new RacingGameService());
        racingGameController.run();
    }
}
