package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.dao.console.ConsoleCarDao;
import racingcar.dao.console.ConsoleGameDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleApplication {

    public static void main(String[] args) {
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(
                new InputView(),
                new OutputView(),
                new RacingCarService(new ConsoleGameDao(), new ConsoleCarDao())
        );

        racingGameConsoleController.execute();
    }
}
