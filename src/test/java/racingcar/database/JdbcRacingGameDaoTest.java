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
class JdbcRacingGameDaoTest {

    private final JdbcRacingGameDao jdbcRacingGameDao;

    private JdbcRacingGameDaoTest(@Autowired final JdbcTemplate jdbcTemplate) {
        this.jdbcRacingGameDao = new JdbcRacingGameDao(jdbcTemplate);
    }

    @Test
    void gameInsertTest() {
        final int count = jdbcRacingGameDao.selectGameIds().size();

        Assertions.assertDoesNotThrow(() -> jdbcRacingGameDao.insert(10));
        assertThat(jdbcRacingGameDao.selectGameIds().size()).isEqualTo(count + 1);
    }
}
