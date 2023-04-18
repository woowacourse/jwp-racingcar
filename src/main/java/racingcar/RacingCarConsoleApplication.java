package racingcar;

import racingcar.controller.RacingCarController;
import racingcar.view.RacingCarView;
import racingcar.view.RacingCarViewImpl;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingCarView racingCarView = new RacingCarViewImpl();
        RacingCarController racingCarController = new RacingCarController(racingCarView);
        racingCarController.start();
    }
}
