package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.RacingConsoleController;

@SpringBootApplication
public class RacingConsoleApplication implements CommandLineRunner {

    @Autowired
    private RacingConsoleController racingConsoleController;

    public static void main(String[] args) {
        SpringApplication.run(RacingConsoleApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        racingConsoleController.run();
    }
}
