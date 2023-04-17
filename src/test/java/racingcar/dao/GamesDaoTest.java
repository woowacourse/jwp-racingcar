package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.TestDatabaseConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestDatabaseConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GamesDaoTest {

    @Autowired
    private GamesDao gamesDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("게임 추가 테스트")
    @Transactional
    void insert_test() {
        final int gameId = gamesDao.insert(5);

        final String sql = "select trial_count from games where id=?";
        final Integer trialCount = jdbcTemplate.queryForObject(sql, Integer.class, gameId);

        assertThat(trialCount).isEqualTo(5);
    }

    @Test
    @DisplayName("진행한 게임 id조회 테스트")
    @Transactional
    void find_ids_test() {
        gamesDao.insert(5);
        gamesDao.insert(4);
        gamesDao.insert(9);

        final List<Integer> allGameIds = gamesDao.findAllGameIds();

        assertThat(allGameIds).hasSize(3);
    }

}
