package racingcar;

import racingcar.controller.console.RacingGameConsoleController;
import racingcar.dao.console.ConsoleCarRepository;
import racingcar.dao.console.ConsoleGameRepository;
import racingcar.dao.console.ConsoleWinnerRepository;
import racingcar.domain.RandomNumberGenerator;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(
                new ConsoleGameRepository(),
                new ConsoleCarRepository(),
                new ConsoleWinnerRepository(),
                new RandomNumberGenerator()
        );
        RacingGameConsoleController controller = new RacingGameConsoleController(new InputView(), new OutputView(),
                racingGameService);
        controller.run();


    }
}
