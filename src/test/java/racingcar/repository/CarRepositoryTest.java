package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.car.CarDao;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;


@JdbcTest
class CarRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarRepository carRepository = new CarRepository(new CarDao(jdbcTemplate));

    private RacingCars racingCars;
    private int playResultId;

    @BeforeEach
    void init() {
        this.carRepository = new CarRepository(new CarDao(jdbcTemplate));
        this.racingCars = RacingCars.makeCars("성하,토니");
        this.playResultId = 1;
    }

    @Test
    @DisplayName("자동차 저장 정보를 받아서 DB에 저장 명령을 보낸다.")
    void saveCar() {
        // when
        carRepository.saveCars(racingCars, playResultId);
        List<Car> findCars = carRepository.findAllByPlayResultId(playResultId);

        // then
        assertThat(findCars.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("결과 ID를 받아서 모든 자동차를 조회한다.")
    void findAll() {
        // when
        carRepository.saveCars(racingCars, playResultId);
        List<Car> findCars = carRepository.findAllByPlayResultId(playResultId);

        Car car1 = findCars.get(0);
        Car car2 = findCars.get(1);

        // then
        assertThat(car1.getName()).isEqualTo("성하");
        assertThat(car2.getName()).isEqualTo("토니");
    }
}
