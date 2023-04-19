package racingcar;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.controller.ConsoleCarController;
import racingcar.dao.CarDao;
import racingcar.dao.DataSourceConfig;
import racingcar.dao.GameDao;
import racingcar.service.CarService;

import javax.sql.DataSource;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final ConsoleCarController consoleCarController = new ConsoleCarController(createCarService());
        consoleCarController.run();
    }

    private static CarService createCarService() {
        final JdbcTemplate jdbcTemplate = getJdbcTemplate();

        final CarDao carDao = new CarDao(jdbcTemplate);
        final GameDao gameDao = new GameDao(jdbcTemplate);

        return new CarService(carDao, gameDao);
    }

    private static JdbcTemplate getJdbcTemplate() {
        final DataSource dateSource = DataSourceConfig.createDateSource();
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dateSource);

        jdbcTemplate.execute("CREATE TABLE GAME(" +
                "    id          BIGINT      NOT NULL AUTO_INCREMENT,\n" +
                "    winners     VARCHAR(50) NOT NULL,\n" +
                "    trial_count INT         NOT NULL DEFAULT 0,\n" +
                "    created_at  DATETIME    NOT NULL DEFAULT current_timestamp,\n" +
                "    PRIMARY KEY (id)\n" +
                ");");
        jdbcTemplate.execute("CREATE TABLE CAR(" +
                "    id       BIGINT     NOT NULL AUTO_INCREMENT,\n" +
                "    game_id  BIGINT     NOT NULL,\n" +
                "    name     VARCHAR(5) NOT NULL,\n" +
                "    position INT        NOT NULL,\n" +
                "    PRIMARY KEY (id),\n" +
                "    FOREIGN KEY (game_id) REFERENCES GAME (id)\n" +
                ");");



        return jdbcTemplate;
    }
}
