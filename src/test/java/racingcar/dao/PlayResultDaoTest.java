package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.PlayResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class PlayResultDaoTest {
    private final JdbcTemplate jdbcTemplate;
    private PlayResultDao playResultDao;

    @Autowired
    public PlayResultDaoTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    public void setUp() {
        this.playResultDao = new PlayResultDao(jdbcTemplate);
    }

    @Test
    void savePlayResult() {
        PlayResult playResult = PlayResult.of(10, "hongo", Timestamp.valueOf(LocalDateTime.now()));
        Integer id = playResultDao.save(playResult);
        PlayResult result = playResultDao.findById(id);
        assertThat(result).isNotNull();
    }
}
