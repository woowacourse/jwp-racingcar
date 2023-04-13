package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GamesDaoTest {

    @Autowired
    private GamesDao gamesDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("게임 추가 테스트")
    void insert_test() {
        final int gameId = gamesDao.insert(5);

        final String sql = "select trial_count from games where id=?";
        final Integer trialCount = jdbcTemplate.queryForObject(sql, Integer.class, gameId);

        assertThat(trialCount).isEqualTo(5);
    }

}
