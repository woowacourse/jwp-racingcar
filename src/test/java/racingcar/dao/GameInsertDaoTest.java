package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
public class GameInsertDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private GameInsertDao jdbcGameInsertDao;

    @BeforeEach
    public void setUp() {
        jdbcGameInsertDao = new JdbcGameInsertDao(jdbcTemplate.getDataSource());
    }

    @Test
    void insertGameTest() {
        // given, when
        int gameId1 = jdbcGameInsertDao.insertGame("jena", 3);
        int gameId2 = jdbcGameInsertDao.insertGame("odo", 6);

        // then
        assertThat(gameId2 - gameId1).isEqualTo(1);
    }
}
