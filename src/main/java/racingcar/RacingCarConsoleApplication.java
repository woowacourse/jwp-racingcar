package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import racingcar.annotation.ConsoleSpringBootApplication;
import racingcar.controller.ConsoleController;

@ConsoleSpringBootApplication
public class RacingCarConsoleApplication implements CommandLineRunner {

    private final ConsoleController consoleController;

    public RacingCarConsoleApplication(ConsoleController consoleController) {
        this.consoleController = consoleController;
    }

    public static void main(String[] args) {
        SpringApplication.run(RacingCarConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            consoleController.plays();
        }
    }

}
