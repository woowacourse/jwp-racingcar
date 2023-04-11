package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.strategy.RandomMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingGameController racingGameController = new RacingGameController(
            new InputView(),
            new OutputView(),
            new RandomMovingStrategy()
        );

        racingGameController.execute();
    }
}
