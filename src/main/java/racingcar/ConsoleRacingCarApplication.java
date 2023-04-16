package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import racingcar.annotation.ConsoleSpringBootApplication;
import racingcar.controller.ConsoleRacingCarController;

//@ConsoleSpringBootApplication
public class ConsoleRacingCarApplication implements CommandLineRunner {
    private final ConsoleRacingCarController consoleRacingCarController;

    public ConsoleRacingCarApplication(ConsoleRacingCarController consoleRacingCarController) {
        this.consoleRacingCarController = consoleRacingCarController;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsoleRacingCarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            consoleRacingCarController.run();
        }
    }
}
