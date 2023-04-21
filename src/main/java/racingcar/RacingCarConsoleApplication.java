package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;
import racingcar.service.GamePlay;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(numberGenerator, new GamePlay());
        racingCarConsoleController.startGame();
    }

}
