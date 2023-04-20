package racing.persist.game;

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
import racing.domain.RacingCarGame;
import racing.persist.car.CarRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RacingGameRepositoryTest {

    @Autowired
    private RacingGameRepository racingGameRepository;
    @Autowired
    private CarRepository carRepository;

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

    @DisplayName("참여한 자동차를 포함한 게임을 조회 할 수 있다.")
    @Test
    void findAllGamesByIdTest() {
        Long savedGameId = racingGameRepository.saveGameByCount(5);
        Cars cars = new Cars(List.of(
                new Car(new CarName("CarA"), 5),
                new Car(new CarName("CarB"), 1),
                new Car(new CarName("CarC"), 2)
        ));
        carRepository.saveCarsInGame(cars, savedGameId);

        RacingCarGame racingGame = racingGameRepository.findRacingGameById(savedGameId);

        Cars gameCars = racingGame.getCars();
        assertThat(gameCars.getCars()).hasSize(3);
        assertThat(gameCars.getCars()).extracting("step")
                .contains(5, 1, 2);
    }

}
