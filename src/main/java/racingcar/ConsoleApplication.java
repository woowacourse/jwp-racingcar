package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.InMemoryCarDao;
import racingcar.dao.InMemoryGameDao;
import racingcar.dao.InMemoryWinnerDao;
import racingcar.services.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(new InMemoryGameDao(), new InMemoryWinnerDao(), new InMemoryCarDao());
        ConsoleController mainController = new ConsoleController(InputView.getInstance(), OutputView.getInstance(), racingGameService);
        mainController.play();
    }
}
