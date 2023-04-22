package racingcar;

import racingcar.controller.ConsoleCarController;
import racingcar.dao.PlayResultInMemoryDao;
import racingcar.dao.PlayerInMemoryDao;
import racingcar.service.CarService;
import racingcar.strategy.RacingRandomNumberGenerator;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        final PlayerInMemoryDao playerDao = new PlayerInMemoryDao();
        final PlayResultInMemoryDao playResultInMemoryDao = new PlayResultInMemoryDao();
        final CarService carService =
                new CarService(new RacingRandomNumberGenerator(), playerDao, playResultInMemoryDao);
        final ConsoleCarController consoleCarController = new ConsoleCarController(carService);

        consoleCarController.run();
    }
}
