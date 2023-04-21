package racingcar;

import racingcar.controller.RacingConsoleController;
import racingcar.dao.CarInMemoryDao;
import racingcar.dao.RacingGameInMemoryDao;
import racingcar.service.RacingGameService;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(new CarInMemoryDao(), new RacingGameInMemoryDao());
        RacingConsoleController racingConsoleController = new RacingConsoleController(racingGameService);
        racingConsoleController.run();
    }
}
