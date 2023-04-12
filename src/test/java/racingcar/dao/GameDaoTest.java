package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameDaoTest {

    private final GameDao gameDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoTest(final GameDao gameDao, final JdbcTemplate jdbcTemplate) {
        this.gameDao = gameDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @DisplayName("시도 횟수를 입력받아 저장한다.")
    @ParameterizedTest(name = "trialCount: {0}")
    @ValueSource(ints = {10, 15, 5, 30})
    void save(final int trialCount) {
        //when
        Long id = gameDao.save(trialCount);
        //then
        String sql = "SELECT trial_count FROM GAME WHERE id = ?";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, id)).isEqualTo(trialCount);
    }
}
