package racingcar;

import racingcar.controller.ConsoleRacingGameController;
import racingcar.dao.GameResultDAOInMemory;
import racingcar.dao.PlayerResultDAOInMemory;
import racingcar.service.RacingGameService;

public class ConsoleApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(
                new GameResultDAOInMemory(), new PlayerResultDAOInMemory()
        );
        ConsoleRacingGameController manager = new ConsoleRacingGameController(racingGameService);

        manager.run();
    }
}
