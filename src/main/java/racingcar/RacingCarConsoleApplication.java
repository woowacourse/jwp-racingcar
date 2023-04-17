package racingcar;

import racingcar.controller.RacingGameConsoleController;

import java.io.IOException;

public class RacingCarConsoleApplication {
    public static void main(String[] args) throws IOException {
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController();
        racingGameConsoleController.run();
    }
}
