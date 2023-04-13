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

}