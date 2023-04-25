package racingcar;

import racingcar.car.model.RandomNumberGenerator;
import racingcar.car.repository.RacingCarInMemoryDAO;
import racingcar.game.controller.ConsoleController;
import racingcar.game.repository.RacingCarGameInMemoryDAO;
import racingcar.game.service.RacingCarGameService;

public class ConsoleApplication {
    
    public static void main(final String[] args) {
        final RacingCarGameService racingCarGameService = buildGameService();
        final ConsoleController consoleController = new ConsoleController(racingCarGameService);
        consoleController.play();
    }
    
    private static RacingCarGameService buildGameService() {
        final RacingCarInMemoryDAO racingCarInMemoryDAO = new RacingCarInMemoryDAO();
        final RacingCarGameInMemoryDAO racingCarGameInMemoryDAO = new RacingCarGameInMemoryDAO();
        return new RacingCarGameService(new RandomNumberGenerator(),
                racingCarGameInMemoryDAO, racingCarInMemoryDAO);
    }
    
}
