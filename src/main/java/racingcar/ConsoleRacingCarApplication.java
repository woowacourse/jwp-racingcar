package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import racingcar.controller.ConsoleRacingCarController;
import racingcar.controller.WebRacingCarController;

@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {WebRacingCarController.class, WebRacingCarApplication.class}
))
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
