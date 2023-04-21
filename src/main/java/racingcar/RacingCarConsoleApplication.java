package racingcar;

import racingcar.controller.console.RacingCarConsoleController;
import racingcar.domain.game.NumberGenerator;
import racingcar.domain.game.RandomNumberGenerator;
import racingcar.repository.ConsoleRacingGameRepository;
import racingcar.repository.RacingGameRepository;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGameRepository racingGameRepository = new ConsoleRacingGameRepository();
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        RacingGameService racingGameService = new RacingGameService(racingGameRepository, randomNumberGenerator);
        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(new InputView(),
                new OutputView(), racingGameService);
        racingCarConsoleController.run();
    }
}
