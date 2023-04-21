package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.RacingGameConsoleController;
import racingcar.service.RacingGameService;

@SpringBootApplication
public class RacingCarWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RacingCarWebApplication.class, args);
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(RacingGameService.generateDefaultRacingGameService());
        racingGameConsoleController.run();
    }

}
