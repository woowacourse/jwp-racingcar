package racingcar;

import racingcar.controller.console.RacingGameConsoleController;
import racingcar.repository.PlayerMemoryRepository;
import racingcar.repository.PlayerRepository;
import racingcar.repository.RacingGameMemoryRepository;
import racingcar.repository.RacingGameRepository;
import racingcar.service.GeneralRacingGameService;
import racingcar.service.RacingGameService;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        final RacingGameConsoleController controller = racingGameConsoleController();

        run(controller);
    }

    private static RacingGameConsoleController racingGameConsoleController() {
        return new RacingGameConsoleController(racingGameService());
    }

    private static RacingGameService racingGameService() {
        return new GeneralRacingGameService(racingGameRepository(), playerRepository());
    }

    private static RacingGameRepository racingGameRepository() {
        return new RacingGameMemoryRepository();
    }

    private static PlayerRepository playerRepository() {
        return new PlayerMemoryRepository();
    }

    public static void run(final RacingGameConsoleController controller) {
        while (controller.run()) {
            /* RUN UNTIL THROW EXCEPTION */
        }
    }
}
