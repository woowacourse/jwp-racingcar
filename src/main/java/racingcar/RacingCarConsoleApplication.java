package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.view.RacingCarView;
import racingcar.view.RacingCarViewImpl;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingCarView racingCarView = new RacingCarViewImpl();
        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(racingCarView);
        racingCarConsoleController.start();
    }
}
