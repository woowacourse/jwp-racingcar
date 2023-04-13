package racingcar;

import racingcar.controller.RacingCarController;
import racingcar.service.RacingCarGame;
import racingcar.view.RacingCarView;
import racingcar.view.RacingCarViewImpl;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingCarGame racingCarGame = new RacingCarGame();
        RacingCarView racingCarView = new RacingCarViewImpl();
        RacingCarController racingCarController = new RacingCarController(racingCarGame, racingCarView);
        racingCarController.start();
    }
}
