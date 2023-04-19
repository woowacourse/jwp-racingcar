package racingcar;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import racingcar.controller.RacingGameController;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car.NumberGenerator;
import racingcar.domain.Car.RandomNumberGenerator;
import racingcar.service.RacingGameService;

import javax.sql.DataSource;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {

        DataSource dataSource = new DriverManagerDataSource();
        GameDao gameDao = new GameDao(dataSource);
        CarDao carDao = new CarDao(dataSource);
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        RacingGameService racingGameService = new RacingGameService(gameDao, carDao, randomNumberGenerator);

        RacingGameController racingCarController = new RacingGameController(racingGameService);
        racingCarController.run();
    }
}
