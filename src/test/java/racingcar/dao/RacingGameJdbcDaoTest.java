package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.RacingGameEntity;

@JdbcTest
class RacingGameJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameJdbcDao(jdbcTemplate);
    }

    @Test
    @DisplayName("racing game을 저장한다")
    void insert() {
        RacingGameEntity racingGameEntity = new RacingGameEntity(5);
        Long gameId = racingGameDao.save(racingGameEntity);

        String sql = "SELECT trial_count FROM racing_game WHERE id = " + gameId;
        Integer savedTrialCount = jdbcTemplate.queryForObject(sql, Integer.class);

        assertThat(savedTrialCount).isEqualTo(5);
    }
}
