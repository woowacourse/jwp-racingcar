package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.util.RandomNumberGenerator;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        ConsoleRacingCarController consoleRacingCarController = new ConsoleRacingCarController(new RandomNumberGenerator());
        consoleRacingCarController.run();
    }
}
