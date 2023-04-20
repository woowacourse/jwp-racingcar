package racing.persistence.car;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import racing.domain.Car;
import racing.domain.CarName;
import racing.persistence.h2.car.CarDao;
import racing.persistence.h2.car.CarEntity;
import racing.persistence.h2.car.H2CarDao;

@TestInstance(Lifecycle.PER_CLASS)
@Import(H2CarDao.class)
@JdbcTest
class H2CarDaoTest {

    @Autowired
    private CarDao carDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM games");
        jdbcTemplate.execute("DELETE FROM cars");
    }

    @DisplayName("자동차를 저장할 수 있다.")
    @Test
    void saveCarTest() {
        Car carA = new Car(new CarName("CarA"), 5);
        Car carB = new Car(new CarName("CarB"), 3);
        Long gameId = 1L;
        List<CarEntity> carEntities = List.of(
                CarEntity.of(gameId, carA, true),
                CarEntity.of(gameId, carB, true)
        );

        carDao.saveAllCar(carEntities);

        Integer carCount = jdbcTemplate.queryForObject("SELECT COUNT(*) from cars", Integer.class);
        assertThat(carCount).isEqualTo(2);
    }

    @DisplayName("게임 아이디로 자동차 조회")
    @Test
    void findAllCarsTest() {
        Car carA = new Car(new CarName("CarA"), 5);
        Car carB = new Car(new CarName("CarB"), 3);
        Car carC = new Car(new CarName("CarC"), 3);
        Long gameAId = 1L;
        Long gameBId = 2L;
        List<CarEntity> carEntities = List.of(
                CarEntity.of(gameAId, carA, true),
                CarEntity.of(gameAId, carB, false),
                CarEntity.of(gameBId, carC, true)
        );
        carDao.saveAllCar(carEntities);

        List<CarEntity> findAllCars = carDao.findCarsInGame(gameAId);
        assertThat(findAllCars).hasSize(2);
        assertThat(findAllCars).extracting("carName")
                .contains("CarA", "CarB");
        assertThat(findAllCars).extracting("step")
                .contains(5, 3);
    }
}
