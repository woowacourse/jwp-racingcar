package racingcar.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@Sql(scripts = {"classpath:data.sql"})
class RacingGameDaoTest {

    private final RacingGameDao racingGameDao;

    private RacingGameDaoTest(@Autowired final JdbcTemplate jdbcTemplate) {
        this.racingGameDao = new RacingGameDao(jdbcTemplate);
    }

    @Test
    void gameInsertTest() {
        final int count = racingGameDao.selectGameIds().size();

        Assertions.assertDoesNotThrow(() -> racingGameDao.insert(10));
        assertThat(racingGameDao.selectGameIds().size()).isEqualTo(count + 1);
    }
}
