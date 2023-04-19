package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.service.RacingGameMemoryService;
import racingcar.service.RacingGameService;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        final RacingGameService racingGameMemoryService = new RacingGameMemoryService();
        final RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(racingGameMemoryService);
        racingGameConsoleController.run();
    }
}
