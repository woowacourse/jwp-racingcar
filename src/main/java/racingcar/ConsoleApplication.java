package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.domain.Game;
import racingcar.view.RacingCarView;
import racingcar.view.RacingCarViewImpl;

public class ConsoleApplication {
    public static void main(String[] args) {
        Game game = new Game();
        RacingCarView racingCarView = new RacingCarViewImpl();
        ConsoleController consoleController = new ConsoleController(game, racingCarView);
        consoleController.start();
    }
}
