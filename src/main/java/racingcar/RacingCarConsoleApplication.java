package racingcar;

import racingcar.controller.RacingConsoleController;
import racingcar.dao.CarInMemoryDao;
import racingcar.dao.RacingGameInMemoryDao;
import racingcar.service.RacingGameAddService;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGameAddService racingGameService = new RacingGameAddService(new CarInMemoryDao(),
                new RacingGameInMemoryDao());
        RacingConsoleController racingConsoleController = new RacingConsoleController(racingGameService);
        racingConsoleController.run();
    }
}
