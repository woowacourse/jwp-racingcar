package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class PlayResultJdbcDaoTest {

    private PlayResultDao playResultDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        playResultDao = new PlayResultJdbcDao(jdbcTemplate);
    }

    @Test
    void insert() {
        playResultDao.insert();

        assertEquals(1, playResultDao.findAllPlayResult().size());
    }

    @Test
    void findAllPlayResult() {
        playResultDao.insert();
        playResultDao.insert();

        assertEquals(2, playResultDao.findAllPlayResult().size());
    }
}