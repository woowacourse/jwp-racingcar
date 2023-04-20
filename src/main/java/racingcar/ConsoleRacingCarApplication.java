package racingcar;

import racingcar.controller.ConsoleRacingGameController;
import racingcar.dao.DummyCarResultDao;
import racingcar.dao.DummyGameResultDao;
import racingcar.service.RacingGameService;

public class ConsoleRacingCarApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(new DummyCarResultDao(), new DummyGameResultDao());
        ConsoleRacingGameController consoleRacingGameController = new ConsoleRacingGameController(racingGameService);
        consoleRacingGameController.playGame();
    }
}
