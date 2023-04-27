package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;
import racingcar.service.RacingCarPlayRule;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(numberGenerator, new RacingCarPlayRule());
        racingCarConsoleController.startGame();
    }

}
