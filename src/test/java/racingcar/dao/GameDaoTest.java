package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.GameEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class GameDaoTest {

    private final GameDao gameDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoTest(final JdbcTemplate jdbcTemplate) {
        this.gameDao = new GameDao(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @DisplayName("전체 게임을 조회한다.")
    @Test
    void findAll() {
        //given
        final String preSql = "INSERT INTO GAME(trial_count) VALUES(?)";
        List<Integer> trialCounts = List.of(10, 15, 20);
        for (Integer trialCount : trialCounts) {
            jdbcTemplate.update(preSql, trialCount);
        }
        //when
        List<GameEntity> allGame = gameDao.findAll();
        //then
        assertThat(allGame).hasSize(3);
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
