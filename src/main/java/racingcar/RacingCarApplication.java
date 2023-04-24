package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.WebController;

@SpringBootApplication
public class RacingCarApplication {
    protected final WebController webController;

    RacingCarApplication(WebController webController) {
        this.webController = webController;
    }
    public static void main(String[] args) {
        SpringApplication.run(RacingCarApplication.class, args);
    }
}
