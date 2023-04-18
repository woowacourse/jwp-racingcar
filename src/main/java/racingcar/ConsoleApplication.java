package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.ConsoleGameDao;
import racingcar.dao.ConsolePlayerDao;
import racingcar.domain.Game;
import racingcar.view.RacingCarView;
import racingcar.view.RacingCarViewImpl;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleController consoleController = createConsoleController();
        consoleController.run();
    }

    private static ConsoleController createConsoleController() {
        Game game = new Game();
        RacingCarView view = new RacingCarViewImpl();
        final GameService service = new GameService(
                new ConsoleGameDao(),
                new ConsolePlayerDao());
        return new ConsoleController(game, view, service);
    }
}
