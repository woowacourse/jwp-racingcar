package racingcar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import racingcar.controller.RacingGameConsoleController;
import racingcar.dao.CarRecordDao;
import racingcar.dao.RacingHistoryDao;
import racingcar.service.RacingGameService;

public class RacingCarConsoleApplication {

    public static void main(String[] args) throws SQLException {
        NamedParameterJdbcTemplate jdbcTemplate = generateJdbcTemplate();
        RacingGameService racingGameService = new RacingGameService(
                new RacingHistoryDao(jdbcTemplate),
                new CarRecordDao(jdbcTemplate));
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(racingGameService);
        racingGameConsoleController.run();
    }

    private static NamedParameterJdbcTemplate generateJdbcTemplate() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;MODE=MySQL", "sa", "");
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
                new SingleConnectionDataSource(connection, false));
        createTables(jdbcTemplate);
        return jdbcTemplate;
    }

    private static void createTables(NamedParameterJdbcTemplate jdbcTemplate) {
        jdbcTemplate.getJdbcOperations().execute("CREATE TABLE IF NOT EXISTS racing_history(\n"
                + "    id BIGINT NOT NULL AUTO_INCREMENT,\n"
                + "    trial_count INT,\n"
                + "    play_time DATETIME,\n"
                + "    PRIMARY KEY (id)\n"
                + ");");

        jdbcTemplate.getJdbcOperations().execute("CREATE TABLE IF NOT EXISTS car_record(\n"
                + "    id BIGINT NOT NULL AUTO_INCREMENT,\n"
                + "    name VARCHAR(255),\n"
                + "    position INT,\n"
                + "    is_winner TINYINT,\n"
                + "    history_id BIGINT NOT NULL,\n"
                + "    PRIMARY KEY (id),\n"
                + "    FOREIGN KEY (history_id) REFERENCES racing_history(id)\n"
                + ");");
    }

}
