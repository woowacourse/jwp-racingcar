package racingcar;

import racingcar.controller.console.RacingGameConsoleController;
import racingcar.dao.console.ConsoleCarDao;
import racingcar.dao.console.ConsoleGameDao;
import racingcar.dao.console.ConsoleWinnerDao;
import racingcar.domain.RandomNumberGenerator;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(
                new ConsoleGameDao(),
                new ConsoleCarDao(),
                new ConsoleWinnerDao(),
                new RandomNumberGenerator()
        );
        RacingGameConsoleController controller = new RacingGameConsoleController(new InputView(), new OutputView(),
                racingGameService);
        controller.run();


    }
}
