package racing.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
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

@TestInstance(Lifecycle.PER_CLASS)
@Import(RacingGameDao.class)
@JdbcTest
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

    @BeforeEach
    void setUp() {
//        racingGameDao = new RacingGameDao(jdbcTemplate);
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
        Car carA = new Car(new CarName("CarA"), 5);
        Car carB = new Car(new CarName("CarB"), 3);
        Long gameId = 1L;
        List<CarEntity> carEntities = List.of(
                CarEntity.of(gameId, carA, true),
                CarEntity.of(gameId, carB, true)
        );

        racingGameDao.saveCar(carEntities);

        Integer carCount = jdbcTemplate.queryForObject("SELECT COUNT(*) from cars", Integer.class);
        assertThat(carCount).isEqualTo(2);
    }

    @DisplayName("저장시간을 기준으로 최신순으로 게임 Id 조회")
    @Test
    void findAllGameIdOrderByRecentTest() throws InterruptedException {
        int gameAmount = 4;
        for (int i = 0; i < gameAmount; i++) {
            Thread.sleep(100); // 동시간대에 저장되어 TIMESTAMP에 동일한 값이 들어가는 경우를 방지
            racingGameDao.saveGame(1);
        }

        List<Long> allGameIds = racingGameDao.findAllGameIdOrderByRecent();
        assertThat(allGameIds).hasSize(gameAmount);
        assertThat(allGameIds).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @DisplayName("모든 자동차 조회")
    @Test
    void findAllCarsTest() {
        Car carA = new Car(new CarName("CarA"), 5);
        Car carB = new Car(new CarName("CarB"), 3);
        Long gameId = 1L;
        List<CarEntity> carEntities = List.of(
                CarEntity.of(gameId, carA, true),
                CarEntity.of(gameId, carB, true)
        );
        racingGameDao.saveCar(carEntities);

        List<CarEntity> findAllCars = racingGameDao.findAllCars();
        assertThat(findAllCars).hasSize(2);
        assertThat(findAllCars).extracting("carName")
                .contains("CarA", "CarB");
    }
}
