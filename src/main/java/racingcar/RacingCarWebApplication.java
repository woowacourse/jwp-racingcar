package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.annotation.WebSpringBootApplication;

@WebSpringBootApplication
public class RacingCarWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RacingCarWebApplication.class, args);
	}

}
