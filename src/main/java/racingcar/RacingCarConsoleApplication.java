package racingcar;

import racingcar.controller.ConsoleCarController;
import racingcar.dao.PlayResultInMemoryDao;
import racingcar.dao.PlayerInMemoryDao;
import racingcar.service.CarService;
import racingcar.strategy.RacingRandomNumberGenerator;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        final RacingRandomNumberGenerator generator = new RacingRandomNumberGenerator();
        final PlayerInMemoryDao playerDao = new PlayerInMemoryDao();
        final PlayResultInMemoryDao playResultInMemoryDao = new PlayResultInMemoryDao();
        final CarService carService = new CarService(generator, playerDao, playResultInMemoryDao);
        final ConsoleCarController consoleCarController = new ConsoleCarController(carService);

        consoleCarController.run();
    }
}
