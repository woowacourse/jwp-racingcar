package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingCarConsoleController racingCarConsoleController =
                new RacingCarConsoleController(new InputView(), new OutputView());
        racingCarConsoleController.run();
    }
}
