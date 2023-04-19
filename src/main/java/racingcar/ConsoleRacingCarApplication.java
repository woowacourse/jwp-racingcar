package racingcar;

import racingcar.controller.ConsoleRacingGameController;
import racingcar.dao.ConsoleCarResultDao;
import racingcar.dao.ConsolePlayResultDao;
import racingcar.service.RacingGameService;

public class ConsoleRacingCarApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(new ConsoleCarResultDao(), new ConsolePlayResultDao());
        ConsoleRacingGameController consoleRacingGameController = new ConsoleRacingGameController(racingGameService);
        consoleRacingGameController.play();
    }
}
