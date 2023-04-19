package racingcar.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import racingcar.model.Car;


@SpringBootTest
@Sql(scripts = {"classpath:data.sql"})
class RacingCarDaoTest {

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;

    @Autowired
    private RacingCarDaoTest(final RacingGameDao racingGameDao, final RacingCarDao racingCarDao) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }

    @Test
    void carInsert() {
        final Car car = new Car("echo", 5);
        final int gameId = racingGameDao.insert(10);

        Assertions.assertDoesNotThrow(() -> racingCarDao.insert(car, gameId, true));
    }
}
