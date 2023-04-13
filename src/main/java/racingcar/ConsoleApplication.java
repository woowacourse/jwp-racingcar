package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.model.manager.ThresholdCarMoveManager;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleController mainController = new ConsoleController(InputView.getInstance(), OutputView.getInstance(), new ThresholdCarMoveManager());
        mainController.play();
    }
}
