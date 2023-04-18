package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@JdbcTest
class DbGameDaoTest {

    private DbGameDao dbGameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        dbGameDao = new DbGameDao(jdbcTemplate.getDataSource());
        jdbcTemplate.execute("ALTER TABLE game ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    void 게임_저장_테스트() {
        long gameId1 = dbGameDao.save(3);
        long gameId2 = dbGameDao.save(5);

        assertAll(
                () -> assertThat(gameId1).isEqualTo(1),
                () -> assertThat(gameId2).isEqualTo(2)
        );
    }
}
