package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.repository.dao.InMemoryGameDao;
import racingcar.repository.dao.InMemoryGameWinnerDao;
import racingcar.repository.dao.InMemoryPlayerDao;
import racingcar.repository.dao.InMemoryPlayerPositionDao;
import racingcar.service.RacingCarGameService;
import racingcar.utils.RandomNumberGenerator;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final RacingCarGameService racingCarGameService = new RacingCarGameService(
                new RandomNumberGenerator(),
                new InMemoryPlayerDao(),
                new InMemoryGameDao(),
                new InMemoryPlayerPositionDao(),
                new InMemoryGameWinnerDao()
        );

        final ConsoleRacingCarController consoleRacingCarController = new ConsoleRacingCarController(racingCarGameService);
        consoleRacingCarController.run();
    }
}
