package racingcar;

import racingcar.controller.RacingConsoleController;
import racingcar.dao.MemoryCarDao;
import racingcar.dao.MemoryGameDao;
import racingcar.service.RacingGameService;

public class RacingConsoleApplication {
    public static void main(String[] args) {
        RacingGameService service = new RacingGameService(new MemoryGameDao(), new MemoryCarDao());
        RacingConsoleController racingConsoleController = new RacingConsoleController(service);
        racingConsoleController.run();
    }
}
