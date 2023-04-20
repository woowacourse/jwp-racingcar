package racingcar.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConsoleController {

    public static void main(String[] args) {
        InputView inputView = new InputView(System.in);
        OutputView outputView = new OutputView();
        try {
            JdbcTemplate jdbcTemplate = getConnection();
            RacingCarService racingCarService = new RacingCarService(new RacingGameDao(jdbcTemplate), new RacingCarDao(jdbcTemplate));
            GameResponseDto plays = racingCarService.plays(new GameRequestDtoForPlays(inputView.requestCarNames(), inputView.requestNumberOfTimes()));
            outputView.printResult(plays);
        } catch (Exception exception) {
            outputView.printErrorMessage(exception);
        }
    }

    public static JdbcTemplate getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;MODE=MySQL");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(
                new SingleConnectionDataSource(connection, false)
        );
        createTable(jdbcTemplate);
        return jdbcTemplate;
    }

    public static void createTable(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(
                "CREATE TABLE GAME (\n" +
                "id                  INT         NOT NULL AUTO_INCREMENT,\n" +
                "count               INT         NOT NULL,\n" +
                "winners             VARCHAR(50) NOT NULL,\n" +
                "created_at          DATETIME    NOT NULL,\n" +
                "PRIMARY KEY (id));");

        jdbcTemplate.execute(
                "CREATE TABLE CAR (\n" +
                "id                  INT         NOT NULL AUTO_INCREMENT,\n" +
                "name                VARCHAR(50) NOT NULL,\n" +
                "position            INT         NOT NULL,\n" +
                "game_id      INT         NOT NULL,\n" +
                "PRIMARY KEY (id), " +
                "CONSTRAINT `car_ibfk_1` FOREIGN KEY (game_id) REFERENCES GAME (id) ON DELETE CASCADE);");
    }

}
