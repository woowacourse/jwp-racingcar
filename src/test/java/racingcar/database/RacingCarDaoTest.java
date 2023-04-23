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
class RacingCarDaoTest {

    private static Car car1;
    private static Car car2;
    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;


    private RacingCarDaoTest(@Autowired final JdbcTemplate jdbcTemplate) {
        this.racingGameDao = new RacingGameDao(jdbcTemplate);
        this.racingCarDao = new RacingCarDao(jdbcTemplate);
    }

    @BeforeAll
    static void setUp() {
        car1 = new Car("echo", 5);
        car2 = new Car("io", 3);
    }

    @Test
    void carInsertTest() {
        final int gameId = racingGameDao.insert(10);

        Assertions.assertDoesNotThrow(() -> racingCarDao.insert(car1, gameId, true));
    }


    @Test
    void selectWinnersTest() {
        final int gameId = racingGameDao.insert(10);

        racingCarDao.insert(car1, gameId, true);
        racingCarDao.insert(car2, gameId, false);

        assertThat(racingCarDao.selectWinners(gameId)).isEqualTo(List.of(car1.getName()));
    }

    @Test
    void selectCarsBy() {
        final int gameId = racingGameDao.insert(10);

        racingCarDao.insert(car1, gameId, true);
        racingCarDao.insert(car2, gameId, false);

        assertThat(racingCarDao.selectCarsBy(gameId).get(0).getName()).isEqualTo(car1.getName());
        assertThat(racingCarDao.selectCarsBy(gameId).get(0).getPosition()).isEqualTo(car1.getPosition());
        assertThat(racingCarDao.selectCarsBy(gameId).get(1).getName()).isEqualTo(car2.getName());
        assertThat(racingCarDao.selectCarsBy(gameId).get(1).getPosition()).isEqualTo(car2.getPosition());
    }
}
