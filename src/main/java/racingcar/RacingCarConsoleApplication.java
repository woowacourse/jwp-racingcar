package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.service.RacingGameManager;
import racingcar.service.RacingGamePlayConsoleService;
import racingcar.service.RacingGamePlayService;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGamePlayService racingPlayService = new RacingGamePlayConsoleService(new RacingGameManager());
        RacingGameConsoleController racingConsoleController = new RacingGameConsoleController(racingPlayService);
        racingConsoleController.run();
    }
}
