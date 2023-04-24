package racingcar;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.controller.ConsoleCarController;
import racingcar.config.DataSourceConfig;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.service.CarService;
import racingcar.strategy.RacingNumberGenerator;
import racingcar.strategy.RacingRandomNumberGenerator;

public class ConsoleRacingCarApplication {

	public static void main(String[] args) {
		final ConsoleCarController consoleCarController = new ConsoleCarController(createCarService());
		consoleCarController.run();
	}

	private static CarService createCarService() {
		final JdbcTemplate jdbcTemplate = getJdbcTemplate();
		createTables(jdbcTemplate);

		final PlayerDao playerDao = new PlayerDao(jdbcTemplate);
		final GameDao gameDao = new GameDao(jdbcTemplate);
		final RacingNumberGenerator generator = new RacingRandomNumberGenerator();

		return new CarService(playerDao, gameDao, generator);
	}

	private static JdbcTemplate getJdbcTemplate() {
		final DataSource dateSource = DataSourceConfig.createDateSource();

		return new JdbcTemplate(dateSource);
	}

	private static void createTables(final JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute("CREATE TABLE GAME(" +
			"    id          BIGINT      NOT NULL AUTO_INCREMENT,\n" +
			"    winners     VARCHAR(50) NOT NULL,\n" +
			"    trial_count INT         NOT NULL DEFAULT 0,\n" +
			"    created_at  DATETIME    NOT NULL DEFAULT current_timestamp,\n" +
			"    PRIMARY KEY (id)\n" +
			");");

		jdbcTemplate.execute("CREATE TABLE PLAYER(" +
			"    id       BIGINT     NOT NULL AUTO_INCREMENT,\n" +
			"    game_id  BIGINT     NOT NULL,\n" +
			"    name     VARCHAR(5) NOT NULL,\n" +
			"    position INT        NOT NULL,\n" +
			"    PRIMARY KEY (id),\n" +
			"    FOREIGN KEY (game_id) REFERENCES GAME (id)\n" +
			");");
	}
}
