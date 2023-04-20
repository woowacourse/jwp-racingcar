package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.dao.car.MemoryCarDao;
import racingcar.dao.game.MemoryGameDao;
import racingcar.dao.winner.MemoryWinnerDao;
import racingcar.service.ConsoleRacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {
    public static void main(String[] args) {
        final MemoryGameDao gameDao = new MemoryGameDao();
        final MemoryCarDao carDao = new MemoryCarDao();
        final MemoryWinnerDao winnerDao = new MemoryWinnerDao();
        final ConsoleRacingCarService racingCarService = new ConsoleRacingCarService(gameDao, carDao, winnerDao);
        
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final ConsoleRacingCarController consoleRacingCarController = new ConsoleRacingCarController(racingCarService, inputView, outputView);
        consoleRacingCarController.run();
    }
}
