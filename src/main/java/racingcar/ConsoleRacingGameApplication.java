package racingcar;

import racingcar.controller.ConsoleRacingGameController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingGameApplication {
    public static void main(String[] args) {
        ConsoleRacingGameController consoleRacingGameController = new ConsoleRacingGameController();
        consoleRacingGameController.execute(new InputView(), new OutputView());
    }
}
