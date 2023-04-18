package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.strategy.RandomMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleApplication {

    public static void main(String[] args) {
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(
                new InputView(),
                new OutputView(),
                new RandomMovingStrategy()
        );

        racingGameConsoleController.execute();
    }
}
