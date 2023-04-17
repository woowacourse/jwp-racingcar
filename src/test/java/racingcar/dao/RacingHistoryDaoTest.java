package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    @DisplayName("게임 실행기록을 저장할 수 있다.")
    @Test
    void insertRacingHistory() {
        //given
        int trialCount = 10;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
        //when
        Long savedId = racingHistoryDao.save(trialCount, now);
        //then
        RacingHistory racingHistory = jdbcTemplate.queryForObject(
            "SELECT id, trial_count, play_time FROM racing_history WHERE id = :id",
            new MapSqlParameterSource("id", savedId),
            (rs, rowNum) -> new RacingHistory(rs.getLong("id"),
                    rs.getInt("trial_count"),
                    rs.getObject("play_time", LocalDateTime.class)));

        assertAll(
            () -> assertThat(racingHistory.getPlayTime().format(timeFormatter)).isEqualTo(
                now.format(timeFormatter)),
            () -> assertThat(racingHistory.getTrialCount()).isEqualTo(trialCount)
        );
    }

    @DisplayName("모든 누적 게임 실행기록을 조회할 수 있다.")
    @Test
    void selectRacingGameHistoryById() {
        //given
        int trialCount = 10;
        LocalDateTime playTime = LocalDateTime.of(2023, 4, 17, 15, 19, 30);
        Long savedId1 = racingHistoryDao.save(trialCount, playTime);
        Long savedId2 = racingHistoryDao.save(trialCount, playTime);

        //when
        List<RacingHistory> racingHistories = racingHistoryDao.findAll();

        //then
        assertAll(
                () -> assertThat(racingHistories).hasSize(2),
                () -> assertThat(racingHistories.get(0).getId()).isEqualTo(savedId1),
                () -> assertThat(racingHistories.get(1).getId()).isEqualTo(savedId2)
        );
    }

}