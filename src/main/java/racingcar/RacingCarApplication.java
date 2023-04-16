package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.ConsoleRacingGameController;

@SpringBootApplication
public class RacingCarApplication implements CommandLineRunner {

    private final ConsoleRacingGameController controller;

    public RacingCarApplication(ConsoleRacingGameController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        SpringApplication.run(RacingCarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        controller.run();
        System.exit(1);
    }
}
