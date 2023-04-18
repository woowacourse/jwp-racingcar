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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.domain.cars.RacingCar;

@JdbcTest
class RacingCarRecordDaoTest {

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
                        rs.getLong("history_id")
                )
        );

        assertAll(
                () -> assertThat(foundCar.getName()).isEqualTo(carName),
                () -> assertThat(foundCar.getPosition()).isZero(),
                () -> assertThat(foundCar.isWinner()).isEqualTo(isWinner),
                () -> assertThat(foundCar.getHistoryId()).isEqualTo(racingHistoryId)
        );

    }

    @DisplayName("자동차 이동 기록을 조회한다.")
    @Test
    void selectCarRecord() {
        //given
        String carName = "Rosie";
        RacingCar car = new RacingCar(carName);
        boolean isWinner = false;
        racingCarRecordDao.insert(racingHistoryId, car, isWinner);
        //when
        List<RacingCarRecord> carRecords = racingCarRecordDao.findByHistoryId(racingHistoryId);
        //then
        assertAll(
                () -> assertThat(carRecords).hasSize(1),
                () -> assertThat(carRecords.get(0).getName()).isEqualTo(carName),
                () -> assertThat(carRecords.get(0).getPosition()).isEqualTo(0),
                () -> assertThat(carRecords.get(0).isWinner()).isEqualTo(isWinner)
        );
    }

}
