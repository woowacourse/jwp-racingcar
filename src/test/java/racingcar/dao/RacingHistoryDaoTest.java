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
class RacingHistoryDaoTest {
    private RacingHistoryDao racingHistoryDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        racingHistoryDao = new RacingHistoryDao(jdbcTemplate);
    }

    @DisplayName("게임 실행기록을 저장할 수 있다.")
    @Test
    void insertRacingHistory() {
        //given
        int trialCount = 10;
        LocalDateTime now = LocalDateTime.now();

        //when
        Long savedId = racingHistoryDao.save(trialCount, now);
        //then
        RacingHistory racingHistory = jdbcTemplate.queryForObject(
                "SELECT trial_count, play_time FROM racing_game WHERE id = :id",
                new MapSqlParameterSource("id", savedId),
                (rs, rowNum) -> new RacingHistory(rs.getInt("trial_count"),
                        rs.getTimestamp("play_time").toLocalDateTime()));

        assertAll(
                () -> assertThat(racingHistory.getPlayTime()).isEqualTo(now),
                () -> assertThat(racingHistory.getTrialCount()).isEqualTo(trialCount)
        );

    }

    @DisplayName("저장한 게임 실행기록과 조회한 기록이 동일하다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 100})
    void selectRacingGame(int trialCount) {

        //given
        LocalDateTime now = LocalDateTime.now();
        racingHistoryDao.save(trialCount, now);
        //when
        List<RacingHistory> racingHistories = racingHistoryDao.findAll();
        //then
        assertAll(
                () -> assertThat(racingHistories.get(0).getPlayTime()).isEqualTo(now),
                () -> assertThat(racingHistories.get(0).getTrialCount()).isEqualTo(trialCount)
        );
    }

    @DisplayName("모든 게임 실행기록을 조회할 수 있다.")
    @Test
    void selectAllRacingGame() {
        //given
        int insertCount = 10;
        for (int i = 0; i < insertCount; i++) {
            racingHistoryDao.save(10, LocalDateTime.now());
        }
        //when
        List<RacingHistory> racingHistories = racingHistoryDao.findAll();
        //then
        assertThat(racingHistories).hasSize(insertCount);
    }
}