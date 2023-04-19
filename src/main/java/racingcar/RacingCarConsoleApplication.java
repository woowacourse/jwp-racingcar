package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.console.RacingGameConsoleController;

@SpringBootApplication
public class RacingCarConsoleApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RacingCarConsoleApplication.class, args);
    }

    private final RacingGameConsoleController consoleController;

    public RacingCarConsoleApplication(final RacingGameConsoleController consoleController) {
        this.consoleController = consoleController;
    }

    @Override
    public void run(final String... args) throws Exception {
        while (consoleController.run()) {
            /* RUN UNTIL THROW EXCEPTION */
        }
    }
}
