package racingcar;

import racingcar.controller.RacingController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        RacingController racingController = new RacingController(new InputView(), new OutputView());
        racingController.run();
    }
}
