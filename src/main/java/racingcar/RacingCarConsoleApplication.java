package racingcar;

import java.io.IOException;

import racingcar.controller.RacingCarConsoleController;

public class RacingCarConsoleApplication {

    public static void main(String[] args) throws IOException {
        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController();
        racingCarConsoleController.run();
    }
}
