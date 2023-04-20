package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.CarDao;
import racingcar.dao.GamesDao;
import racingcar.dao.WinnerDao;

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
