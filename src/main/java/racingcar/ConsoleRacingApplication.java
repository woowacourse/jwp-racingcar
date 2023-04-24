package racingcar;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.ConsoleRacingController;

@SpringBootApplication
public class ConsoleRacingApplication {

    public static void main(String[] args) {
        ConsoleRacingController consoleRacingController = new ConsoleRacingController();
        consoleRacingController.run();
    }
}
