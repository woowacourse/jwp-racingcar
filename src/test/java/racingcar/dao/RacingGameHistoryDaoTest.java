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
                "SELECT trial_count, play_time FROM racing_history WHERE id = :id",
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

}
