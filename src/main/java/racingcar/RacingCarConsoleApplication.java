package racingcar;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.controller.RacingGameController;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car.NumberGenerator;
import racingcar.domain.Car.RandomNumberGenerator;
import racingcar.service.RacingGameService;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        GameDao gameDao = new GameDao(jdbcTemplate);
        CarDao carDao = new CarDao(jdbcTemplate);
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        RacingGameService racingGameService = new RacingGameService(gameDao, carDao, randomNumberGenerator);

        RacingGameController racingCarController = new RacingGameController(racingGameService);
        racingCarController.run();
    }
}
