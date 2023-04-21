package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(numberGenerator);
        racingCarConsoleController.gameStart();
    }

}
