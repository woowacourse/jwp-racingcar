package racing.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RacingGameDaoTest {

    @Autowired
    private RacingGameDao racingGameDao;

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
                "count int(3) NOT NULL," +
                "create_time timestamp NOT NULL," +
                "PRIMARY KEY (game_id)" +
                ");");
    }

    private void createCarTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cars (" +
                "car_id bigint NOT NULL AUTO_INCREMENT," +
                "car_name VARCHAR(8) NOT NULL," +
                "step int(3) NOT NULL," +
                "winner boolean default false," +
                "game_id bigint not null," +
                "PRIMARY KEY (car_id)" +
                ");");
    }

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM games");
        jdbcTemplate.execute("DELETE FROM cars");
    }

    @DisplayName("시도 횟수를 통해 새로운 게임을 생성할 수 있다")
    @Test
    void saveGameTest() {
        int trialCount = 5;

        Long gameId = racingGameDao.saveGame(trialCount);

        int gameTrialById = racingGameDao.findGameTrialById(gameId);
        assertThat(gameTrialById).isEqualTo(trialCount);
    }

    @DisplayName("자동차를 저장할 수 있다.")
    @Test
    void saveCarTest() {
        Car carA = new Car("CarA", 5);
        Car carB = new Car("CarB", 3);
        Long gameId = 1L;
        List<CarEntity> carEntities = List.of(
                CarEntity.of(gameId, carA, true),
                CarEntity.of(gameId, carB, true)
        );

        racingGameDao.saveCar(carEntities);

        Integer carCount = jdbcTemplate.queryForObject("SELECT COUNT(*) from cars", Integer.class);
        assertThat(carCount).isEqualTo(2);
    }
}
