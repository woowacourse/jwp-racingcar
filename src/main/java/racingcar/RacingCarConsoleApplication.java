package racingcar;

import java.io.IOException;

import racingcar.controller.RacingGameConsoleController;

public class RacingCarConsoleApplication {

    public static void main(String[] args) throws IOException {
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController();
        racingGameConsoleController.run();
    }
}
