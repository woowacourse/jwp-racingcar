package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public final class RepositoryFactory {

    public static CarDao carDao(final JdbcTemplate jdbcTemplate) {
        return new CarDao(jdbcTemplate);
    }

    public static WinnerDao winnerDao(final JdbcTemplate jdbcTemplate) {
        return new WinnerDao(jdbcTemplate);
    }

    public static GamesDao gamesDao(final JdbcTemplate jdbcTemplate) {
        return new GamesDao(jdbcTemplate);
    }
}
