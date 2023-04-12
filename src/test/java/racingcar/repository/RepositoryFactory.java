package racingcar.repository;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.service.CarsRepository;
import racingcar.service.GamesRepository;

public final class RepositoryFactory {

    public static CarsRepository carsRepository(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        return new CarsRepositoryImpl(carDao(dataSource, jdbcTemplate), winnerDao(dataSource, jdbcTemplate));
    }

    public static GamesRepository gamesRepository(final DataSource dataSource) {
        return new GamesRepositoryImpl(gamesDao(dataSource));
    }

    public static CarDao carDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        return new CarDao(insertCarDao(dataSource), selectCarDao(jdbcTemplate));
    }

    public static InsertCarDao insertCarDao(final DataSource dataSource) {
        return new InsertCarDao(dataSource);
    }

    public static SelectCarDao selectCarDao(final JdbcTemplate jdbcTemplate) {
        return new SelectCarDao(jdbcTemplate);
    }

    public static WinnerDao winnerDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        return new WinnerDao(insertWinnerDao(dataSource), selectWinnerDao(jdbcTemplate));
    }

    public static SelectWinnerDao selectWinnerDao(final JdbcTemplate jdbcTemplate) {
        return new SelectWinnerDao(jdbcTemplate);
    }

    public static InsertWinnerDao insertWinnerDao(final DataSource dataSource) {
        return new InsertWinnerDao(dataSource);
    }

    public static GamesDao gamesDao(final DataSource dataSource) {
        return new GamesDao(new InsertGameDao(dataSource));
    }
}
