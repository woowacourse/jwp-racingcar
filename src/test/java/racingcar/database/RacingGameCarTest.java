package racingcar.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racingcar.model.Car;

@JdbcTest
@Sql(scripts = {"classpath:data.sql"})
class RacingGameCarTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void carInsert() {
        final RacingCarDao carDao = new RacingCarDao(jdbcTemplate);
        final RacingGameDao gameDao = new RacingGameDao(jdbcTemplate);

        final Car car = new Car("echo", 5);
        gameDao.insert(10, "echo");
        final int gameId = gameDao.getGameId();

        Assertions.assertDoesNotThrow(() -> carDao.insert(car, gameId));
    }
}
