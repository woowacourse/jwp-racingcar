package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.domain.RandomNumberGenerator;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final ConsoleRacingCarController consoleController = new ConsoleRacingCarController();
        consoleController.run(new RandomNumberGenerator());
    }
}
