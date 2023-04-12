package racingcar;

import racingcar.controller.RacingGameController;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGameController racingCarController = new RacingGameController();
        racingCarController.run();
    }
}
