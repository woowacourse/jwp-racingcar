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
import racingcar.domain.car.Car;
import racingcar.dto.CarRecordDto;

@JdbcTest
class CarRecordDaoTest {

    private CarRecordDao carRecordDao;
    private long racingHistoryId;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        carRecordDao = new CarRecordDao(jdbcTemplate);
        RacingHistoryDao racingHistoryDao = new RacingHistoryDao(jdbcTemplate);
        racingHistoryId = racingHistoryDao.save(10, LocalDateTime.now());
    }

    @DisplayName("자동차 이동 기록을 저장한다.")
    @Test
    void insertCar() {
        //given
        String carName = "Rosie";
        Car car = new Car(carName);
        boolean isWinner = true;
        //when
        Long savedId = carRecordDao.save(racingHistoryId, car, isWinner);
        //then
        CarRecordDto foundCar = jdbcTemplate.queryForObject(
                "SELECT * FROM car_record WHERE id = :id",
                new MapSqlParameterSource("id", savedId),
                (rs, rowNum) -> new CarRecordDto(
                        rs.getString("name"),
                        rs.getInt("position"),
                        rs.getBoolean("is_winner")
                )
        );
        assertAll(
                () -> assertThat(foundCar.getName()).isEqualTo(carName),
                () -> assertThat(foundCar.getPosition()).isZero(),
                () -> assertThat(foundCar.isWinner()).isEqualTo(isWinner)
                );
    }

    @DisplayName("한 게임에 있는 모든 자동차 이동 기록을 조회한다.")
    @Test
    void findAllByHistoryId() {
        //given
        String carName1 = "Rosie";
        String carName2 = "Baron";
        carRecordDao.save(racingHistoryId, new Car(carName1), true);
        carRecordDao.save(racingHistoryId, new Car(carName2), false);

        //when
        List<CarRecordDto> carRecords = carRecordDao.findAllByRacingHistoryId(racingHistoryId);

        //then
        assertAll(
                () -> assertThat(carRecords).hasSize(2),
                () -> assertThat(carRecords.get(0).getName()).isEqualTo("Rosie"),
                () -> assertThat(carRecords.get(1).getName()).isEqualTo("Baron")
        );
    }

}