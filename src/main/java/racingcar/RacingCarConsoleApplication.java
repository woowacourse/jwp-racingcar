package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.service.RacingGameConsoleService;
import racingcar.service.RacingGameService;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        final RacingGameService racingGameConsoleService = new RacingGameConsoleService();
        final RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(racingGameConsoleService);
        racingGameConsoleController.run();
    }
}
