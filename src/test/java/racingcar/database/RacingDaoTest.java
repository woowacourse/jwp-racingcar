package racingcar.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.model.Car;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class RacingDaoTest {

    private RacingCarDao carDao;
    private RacingGameDao gameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        carDao = new RacingCarDao(jdbcTemplate);
        gameDao = new RacingGameDao(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE racing_car IF EXISTS");
        jdbcTemplate.execute("DROP TABLE racing_game IF EXISTS");

        jdbcTemplate.execute("CREATE TABLE racing_game (" +
                "    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "    trial_count INT         NOT NULL," +
                "    winners     VARCHAR(50) NOT NULL," +
                "    created_at  DATETIME    NOT NULL default current_timestamp" +
                ")");
        jdbcTemplate.execute("CREATE TABLE racing_car (" +
                "    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "    name        VARCHAR(5)  NOT NULL," +
                "    position    INT         NOT NULL," +
                "    racing_game_id  INT     NOT NULL," +
                "    FOREIGN KEY (racing_game_id) REFERENCES racing_game(id) ON DELETE CASCADE" +
                ")");
    }

    @Test
    void gameInsert() {
        final int trialCount = 10;
        final String winners = "io,echo";
        Assertions.assertDoesNotThrow(() -> gameDao.insert(trialCount, winners));
    }

    @Test
    void carinsert() {
        gameDao.insert(10, "echo");
        final Car car = new Car("echo", 5);
        final int gameId = 1;

        Assertions.assertDoesNotThrow(() -> carDao.insert(car, gameId));
    }

    @Test
    void Key() {
        final int trialCount = 10;
        final String winners = "io,echo";
        final int id = gameDao.insert(trialCount, winners);
        assertThat(id).isEqualTo(1);
    }

}