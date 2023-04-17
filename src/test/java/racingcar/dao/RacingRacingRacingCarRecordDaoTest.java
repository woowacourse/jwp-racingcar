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
import racingcar.domain.cars.RacingCar;

@JdbcTest
class RacingRacingRacingCarRecordDaoTest {

    private RacingCarRecordDao racingCarRecordDao;
    private long racingHistoryId;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        racingCarRecordDao = new RacingCarRecordDao(jdbcTemplate);
        RacingGameHistoryDao racingGameHistoryDao = new RacingGameHistoryDao(jdbcTemplate);
        racingHistoryId = racingGameHistoryDao.insert(10, LocalDateTime.now());
    }

    @DisplayName("자동차 이동 기록을 저장한다.")
    @Test
    void insertCar() {
        //given
        String carName = "Rosie";
        RacingCar racingCar = new RacingCar(carName);
        boolean isWinner = true;
        long historyId = 1L;
        //when
        long insertedRecordId = racingCarRecordDao.insert(racingHistoryId, racingCar, isWinner);
        //then
        RacingCarRecord foundCar = jdbcTemplate.queryForObject(
                "SELECT * FROM car_record WHERE id = :id",
                new MapSqlParameterSource("id", insertedRecordId),
                (rs, rowNum) -> new RacingCarRecord(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("position"),
                        rs.getBoolean("is_winner"),
                        historyId
                )
        );

        assertAll(
                () -> assertThat(foundCar.getName()).isEqualTo(carName),
                () -> assertThat(foundCar.getPosition()).isZero(),
                () -> assertThat(foundCar.isWinner()).isEqualTo(isWinner),
                () -> assertThat(foundCar.getHistoryId()).isEqualTo(historyId)
        );

    }

}
