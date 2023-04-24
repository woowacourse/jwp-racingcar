package racing;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import racing.controller.ConsoleRacingGameController;
import racing.dao.RacingCarDao;
import racing.dao.RacingGameDao;
import racing.service.RacingGameService;

public class ConsoleApplication {

    public static void main(String[] args) {
        final JdbcTemplate jdbcTemplate = getJdbcTemplate();
        createTables(jdbcTemplate);

        final RacingGameService racingGameService = new RacingGameService(
                new RacingGameDao(jdbcTemplate),
                new RacingCarDao(jdbcTemplate)
        );

        new ConsoleRacingGameController(racingGameService).start();
    }

    private static JdbcTemplate getJdbcTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL;AUTOCOMMIT=TRUE");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return new JdbcTemplate(dataSource);
    }

    private static void createTables(final JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS CAR (" +
                "id bigint NOT NULL AUTO_INCREMENT,\n" +
                "name VARCHAR(8) NOT NULL,\n" +
                "position int(3) NOT NULL,\n" +
                "is_winner boolean default false,\n" +
                "game_id bigint not null,\n" +
                "PRIMARY KEY (id)" +
                ");"
        );

        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS GAME (" +
                "id bigint NOT NULL AUTO_INCREMENT,\n" +
                "play_count int(3) NOT NULL,\n" +
                "create_time timestamp NOT NULL,\n" +
                "PRIMARY KEY (id)\n" +
                ");"
        );
    }

}
