package racingcar;

import racingcar.console.RacingCarConsoleController;
import racingcar.domain.game.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(new InputView(),
                new OutputView(), new RandomNumberGenerator());
        racingCarConsoleController.run();
    }
}
