package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.PlayRecordEntity;

@JdbcTest
class JdbcPlayRecordsDaoTest {

    private final JdbcTemplate jdbcTemplate;
    private JdbcPlayRecordsDao playRecordsDao;

    @Autowired
    public JdbcPlayRecordsDaoTest(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        playRecordsDao = new JdbcPlayRecordsDao(jdbcTemplate);
    }

    @DisplayName("DB: 게임 이력 저장 테스트")
    @Test
    void insert() {
        playRecordsDao.insert(new PlayRecordEntity(2));

        assertThat(playRecordsDao.getLastId()).isEqualTo(1L);
    }
}
