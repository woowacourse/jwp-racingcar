package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class GameDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private GameDao jdbcGameDao;

    @BeforeEach
    public void setUp() {
        jdbcGameDao = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    void insertGameTest() {
        // given, when
        int gameId1 = jdbcGameDao.insert("jena", 3);
        int gameId2 = jdbcGameDao.insert("odo", 6);

        // then
        assertThat(gameId2 - gameId1).isEqualTo(1);
    }
}
