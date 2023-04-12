package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    class RacingHistory {
        private final int trialCount;
        private final LocalDateTime playTime;

        public RacingHistory(int trialCount, LocalDateTime playTime) {
            this.trialCount = trialCount;
            this.playTime = playTime;
        }

        public int getTrialCount() {
            return trialCount;
        }

        public LocalDateTime getPlayTime() {
            return playTime;
        }
    }

    @DisplayName("게임을 저장할 수 있다.")
    @Test
    void insertRacingGame() {
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

    // 게임 결과 조회

}