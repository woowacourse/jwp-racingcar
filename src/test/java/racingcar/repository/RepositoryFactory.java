package racingcar.repository;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public final class RepositoryFactory {

    public static CarDao carDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        return new CarDao(dataSource, jdbcTemplate);
    }

    public static InsertCarDao insertCarDao(final DataSource dataSource) {
        return new InsertCarDao(dataSource);
    }

    public static WinnerDao winnerDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        return new WinnerDao(dataSource, jdbcTemplate);
    }

    public static GamesDao gamesDao(final DataSource dataSource) {
        return new GamesDao(dataSource);
    }
}
