package racing.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racing.controller.dto.request.CarRequest;
import racing.domain.Car;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;

    @BeforeEach
    void setUp() {
        carDao = new RacingCarDao(jdbcTemplate);
    }

    @DisplayName("차를 저장한다.")
    @Test
    void saveCar() {
        // given
        CarRequest request = CarRequest.of(1L, new Car("bebedd"), true);
        final String query = "select is_winner from car where name = \'bebedd\'";

        // when
        carDao.saveCar(request);
        Boolean resultCount = jdbcTemplate.queryForObject(query, Boolean.class);

        // then
        assertThat(resultCount).isTrue();
    }

    @DisplayName("모든 차를 조회한다.")
    @Test
    void findAll() {
        CarRequest request1 = CarRequest.of(1L, new Car("bebe"), false);
        CarRequest request2 = CarRequest.of(1L, new Car("dd"), true);
        CarRequest request3 = CarRequest.of(1L, new Car("royce"), true);

        // given
        carDao.saveCar(request1);
        carDao.saveCar(request2);
        carDao.saveCar(request3);

        // when
        List<CarEntity> cars = carDao.findAll();

        // then
        assertAll(
                () -> assertThat(cars).hasSize(3),
                () -> assertThat(cars.get(0).getGameId()).isEqualTo(1),
                () -> assertThat(cars.get(1).getGameId()).isEqualTo(1),
                () -> assertThat(cars.get(2).getGameId()).isEqualTo(1),
                () -> assertThat(cars.get(0).isWinner()).isFalse(),
                () -> assertThat(cars.get(1).isWinner()).isTrue(),
                () -> assertThat(cars.get(2).isWinner()).isTrue(),
                () -> assertThat(cars.get(0).getName()).isEqualTo("bebe"),
                () -> assertThat(cars.get(1).getName()).isEqualTo("dd"),
                () -> assertThat(cars.get(2).getName()).isEqualTo("royce")
        );
    }

}
