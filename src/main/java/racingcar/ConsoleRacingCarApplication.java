package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.car.MemoryCarDao;
import racingcar.dao.game.MemoryGameDao;
import racingcar.dao.winner.MemoryWinnerDao;
import racingcar.model.manager.ThresholdCarMoveManager;
import racingcar.services.GameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {
    public static void main(String[] args) {
        GameService gameService = new GameService(
                new MemoryGameDao(),
                new MemoryCarDao(),
                new MemoryWinnerDao(),
                new ThresholdCarMoveManager()
        );
        ConsoleController consoleController = new ConsoleController(new InputView(), new OutputView(), gameService);
        consoleController.run();
    }
}
