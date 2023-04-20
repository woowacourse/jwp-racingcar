package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.RacingGameConsoleController;
import racingcar.dao.JdbcRacingGameRepository;
import racingcar.service.RacingGameService;

@SpringBootApplication
public class RacingCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RacingCarApplication.class, args);
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(new RacingGameService(JdbcRacingGameRepository.generateDefaultJdbcRacingGameRepository()));
        racingGameConsoleController.run();
    }

}
