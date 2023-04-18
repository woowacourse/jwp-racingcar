package racingcar.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:data.sql"})
class RacingGameDaoTest {

    private final RacingGameDao racingGameDao;

    @Autowired
    private RacingGameDaoTest(final RacingGameDao racingGameDao) {
        this.racingGameDao = racingGameDao;
    }

    @Test
    void gameInsert() {
        final int trialCount = 10;
        final String winners = "io,echo";

        Assertions.assertDoesNotThrow(() -> racingGameDao.insert(trialCount, winners));
    }
}
