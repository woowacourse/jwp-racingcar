package racingcar;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import racingcar.controller.ConsoleController;
import racingcar.dao.GameRecordDao;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.service.RacingCarService;

public class ConsoleApplication {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = makeJdbcTemplate();

        GameRecordDao gameRecordDao = new GameRecordDao(jdbcTemplate);
        RacingCarDao racingCarDao = new RacingCarDao(jdbcTemplate);
        ResultDao resultDao = new ResultDao(jdbcTemplate);

        RacingCarService racingCarService = new RacingCarService(resultDao, racingCarDao, gameRecordDao);

        ConsoleController consoleController = new ConsoleController(racingCarService);
        consoleController.runGame();
    }

    private static JdbcTemplate makeJdbcTemplate() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("data.sql")
                .build();
        return new JdbcTemplate(db);
    }
}
