package racingcar.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql(scripts = {"classpath:data.sql"})
class RacingGameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void gameInsert() {
        final RacingGameDao gameDao = new RacingGameDao(jdbcTemplate);
        final int trialCount = 10;
        final String winners = "io,echo";

        final int id = gameDao.insert(trialCount, winners);

        assertThat(id).isEqualTo(1);
    }
}
