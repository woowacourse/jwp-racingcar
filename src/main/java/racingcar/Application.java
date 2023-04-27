package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.dao.InMemoryCarDao;
import racingcar.dao.InMemoryGameResultDao;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(
                InputView.getInstance(), OutputView.getInstance(),
                new RacingGameService(
                        new InMemoryCarDao(), new InMemoryGameResultDao()
                )
        );
        racingGameConsoleController.run();

    }
}

