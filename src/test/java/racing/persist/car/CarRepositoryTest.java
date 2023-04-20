package racing.persist.car;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarDao carDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    void setUpSchema() {
        createCarTable();
        createGameTable();
    }

    private void createGameTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS games (" +
                "game_id bigint NOT NULL AUTO_INCREMENT, " +
                "count int NOT NULL," +
                "create_time timestamp NOT NULL," +
                "PRIMARY KEY (game_id)" +
                ");");
    }

    private void createCarTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cars (" +
                "car_id bigint NOT NULL AUTO_INCREMENT," +
                "car_name VARCHAR(8) NOT NULL," +
                "step int NOT NULL," +
                "winner boolean default false," +
                "game_id bigint not null," +
                "PRIMARY KEY (car_id)" +
                ");");
    }

    @DisplayName("자동차 객체를 저장할 수 있다.")
    @Test
    void saveCarsTest() {
        Cars cars = new Cars(List.of(
                new Car(new CarName("CarA"), 5),
                new Car(new CarName("CarB"), 3),
                new Car(new CarName("CarC"), 3)
        ));
        Long gameId = 1L;

        carRepository.saveCarsInGame(cars, gameId);

        List<CarEntity> savedCarEntities = carDao.findCarsInGame(gameId);

        assertThat(savedCarEntities).hasSize(3);
        assertThat(savedCarEntities).extracting("carName")
                .contains("CarA", "CarB", "CarC");
    }
}
