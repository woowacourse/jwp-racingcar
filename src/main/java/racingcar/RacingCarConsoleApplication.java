package racingcar;

import java.io.IOException;

import racingcar.controller.RacingGameController;

public class RacingCarConsoleApplication {

    public static void main(String[] args) throws IOException {
        RacingGameController racingGameController = new RacingGameController();
        racingGameController.run();
    }
}
