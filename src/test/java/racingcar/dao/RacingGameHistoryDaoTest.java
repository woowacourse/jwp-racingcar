package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@JdbcTest
class RacingGameHistoryDaoTest {
    private RacingGameHistoryDao racingGameHistoryDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        racingGameHistoryDao = new RacingGameHistoryDao(jdbcTemplate);
    }

    @DisplayName("게임 실행기록을 저장할 수 있다.")
    @Test
    void insertRacingHistory() {
        //given
        int trialCount = 10;
        LocalDateTime now = LocalDateTime.now();

        //when
        Long insertedHistoryId = racingGameHistoryDao.insert(trialCount, now);
        //then
        RacingGameHistory racingGameHistory = jdbcTemplate.queryForObject(
                "SELECT id, trial_count, play_time FROM racing_history WHERE id = :id",
                new MapSqlParameterSource("id", insertedHistoryId),
                (rs, rowNum) -> new RacingGameHistory(
                        rs.getLong("id"),
                        rs.getInt("trial_count"),
                        rs.getTimestamp("play_time").toLocalDateTime()
                )
        );

        assertAll(
                () -> assertThat(racingGameHistory.getPlayTime()).isEqualTo(now),
                () -> assertThat(racingGameHistory.getTrialCount()).isEqualTo(trialCount)
        );

    }

    @DisplayName("저장한 게임 실행기록과 조회한 기록이 동일하다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 100})
    void selectRacingGame(int trialCount) {

        //given
        LocalDateTime now = LocalDateTime.now();
        racingGameHistoryDao.insert(trialCount, now);
        //when
        List<RacingGameHistory> racingHistories = racingGameHistoryDao.selectAll();
        //then
        assertAll(
                () -> assertThat(racingHistories.get(0).getPlayTime()).isEqualTo(now),
                () -> assertThat(racingHistories.get(0).getTrialCount()).isEqualTo(trialCount)
        );
    }

    @DisplayName("게임 실행 이력을 조회할 수 있다.")
    @Test
    void testSelectAll() {
        //given
        int insertCount = 10;
        for (int i = 0; i < insertCount; i++) {
            racingGameHistoryDao.insert(10, LocalDateTime.now());
        }
        //when
        List<RacingGameHistory> racingGameHistories = racingGameHistoryDao.selectAll();
        //then
        assertThat(racingGameHistories).hasSize(insertCount);
    }

}
