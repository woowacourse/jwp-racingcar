package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.dao.InMemoryGameDao;
import racingcar.dao.InMemoryPlayerDao;
import racingcar.service.RacingGameService;

public class Application {

    public static void main(String[] args) {
        RacingGameConsoleController racingGameController =
                new RacingGameConsoleController(new RacingGameService(new InMemoryGameDao(), new InMemoryPlayerDao()));
        racingGameController.run();
    }
}
