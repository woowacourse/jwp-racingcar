package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.util.List;
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
        String sql = "INSERT into RACING_GAME (trial_count, created_at) values (?,?)";

        jdbcTemplate.update(sql, 10, LocalDateTime.now());
        jdbcTemplate.update(sql, 9, LocalDateTime.now());
        jdbcTemplate.update(sql, 8, LocalDateTime.now());
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

    @Test
    @DisplayName("전체 게임들을 생성순으로 조회한다.")
    void findAll() {
        List<RacingGameEntity> racingGameEntities = racingGameDao.findAllByCreatedTimeAsc();

        assertAll(
                () -> assertThat(racingGameEntities).hasSize(3),
                () -> assertThat(racingGameEntities)
                        .extracting("trialCount").containsExactly(8, 9, 10)
        );
    }
}
