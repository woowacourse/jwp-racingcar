package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.console.RacingGameConsoleController;

@SpringBootApplication
public class RacingCarApplication implements CommandLineRunner {

	@Autowired
	RacingGameConsoleController racingGameConsoleController;

	public static void main(String[] args) {
		SpringApplication.run(RacingCarApplication.class, args);
	}

	@Override
	public void run (final String... args) throws Exception {
		racingGameConsoleController.run();
	}
}
