package racingcar;

import racingcar.controller.NameParser;
import racingcar.controller.RacingGameConsoleController;

public class ConsoleApplication {

    public static void main(String[] args) {
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(new NameParser());
        racingGameConsoleController.play();
    }
}
