package racingcar;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import racingcar.controller.RacingConsoleController;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.service.RacingGameService;

public class RacingConsoleApplication {

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = createJdbcTemplate();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        GameDao gameDao = new GameDao(jdbcTemplate);
        CarDao carDao = new CarDao(namedParameterJdbcTemplate);

        RacingGameService racingGameService = new RacingGameService(gameDao, carDao);

        RacingConsoleController racingConsoleController = new RacingConsoleController(racingGameService);
        racingConsoleController.run();
    }

    private static JdbcTemplate createJdbcTemplate() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("data.sql")
                .build();
        return new JdbcTemplate(db);
    }
}
