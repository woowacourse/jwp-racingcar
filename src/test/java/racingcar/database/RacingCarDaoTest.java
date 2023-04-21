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
class RacingCarDaoTest {

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;

    private RacingCarDaoTest(@Autowired final JdbcTemplate jdbcTemplate) {
        this.racingGameDao = new RacingGameDao(jdbcTemplate);
        this.racingCarDao = new RacingCarDao(jdbcTemplate);
    }

    @Test
    void carInsert() {
        final Car car = new Car("echo", 5);
        final int gameId = racingGameDao.insert(10);

        Assertions.assertDoesNotThrow(() -> racingCarDao.insert(car, gameId, true));
    }
}
