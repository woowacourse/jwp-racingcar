package racingcar.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racingcar.model.Car;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@JdbcTest
@Sql(scripts = {"classpath:data.sql"})
class JdbcRacingCarDaoTest {

    private static Car car1;
    private static Car car2;
    private final JdbcRacingGameDao jdbcRacingGameDao;
    private final JdbcRacingCarDao jdbcRacingCarDao;


    private JdbcRacingCarDaoTest(@Autowired final JdbcTemplate jdbcTemplate) {
        this.jdbcRacingGameDao = new JdbcRacingGameDao(jdbcTemplate);
        this.jdbcRacingCarDao = new JdbcRacingCarDao(jdbcTemplate);
    }

    @BeforeAll
    static void setUp() {
        car1 = new Car("echo", 5);
        car2 = new Car("io", 3);
    }

    @Test
    void carInsertTest() {
        final int gameId = jdbcRacingGameDao.insert(10);

        Assertions.assertDoesNotThrow(() -> jdbcRacingCarDao.insert(car1, gameId, true));
    }


    @Test
    void selectWinnersTest() {
        final int gameId = jdbcRacingGameDao.insert(10);

        jdbcRacingCarDao.insert(car1, gameId, true);
        jdbcRacingCarDao.insert(car2, gameId, false);

        assertThat(jdbcRacingCarDao.selectWinners(gameId)).isEqualTo(List.of(car1.getName()));
    }

    @Test
    void selectCarsBy() {
        final int gameId = jdbcRacingGameDao.insert(10);

        jdbcRacingCarDao.insert(car1, gameId, true);
        jdbcRacingCarDao.insert(car2, gameId, false);

        assertThat(jdbcRacingCarDao.selectCarsBy(gameId).get(0).getName()).isEqualTo(car1.getName());
        assertThat(jdbcRacingCarDao.selectCarsBy(gameId).get(0).getPosition()).isEqualTo(car1.getPosition());
        assertThat(jdbcRacingCarDao.selectCarsBy(gameId).get(1).getName()).isEqualTo(car2.getName());
        assertThat(jdbcRacingCarDao.selectCarsBy(gameId).get(1).getPosition()).isEqualTo(car2.getPosition());
    }
}
