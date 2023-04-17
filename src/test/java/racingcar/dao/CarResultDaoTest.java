package racingcar.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.CarResult;
import racingcar.domain.PlayResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarResultDaoTest {

    private final PlayResultDao playResultDao;

    private final CarResultDao carResultDao;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    CarResultDaoTest(PlayResultDao playResultDao, CarResultDao carResultDao, JdbcTemplate jdbcTemplate) {
        this.playResultDao = playResultDao;
        this.carResultDao = carResultDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE car_result IF EXISTS");
        jdbcTemplate.execute("DROP TABLE play_result IF EXISTS");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS play_result" +
                "(" +
                "    id         INT         NOT NULL AUTO_INCREMENT," +
                "    trial_count INT         NOT NULL," +
                "    winners    VARCHAR(120) NOT NULL," +
                "    created_at DATETIME    NOT NULL," +
                "    PRIMARY KEY (id)" +
                ");");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS car_result" +
                "(" +
                "    id             INT         NOT NULL AUTO_INCREMENT," +
                "    play_result_id INT         NOT NULL," +
                "    name           VARCHAR(10) NOT NULL," +
                "    position       INT         NOT NULL," +
                "    PRIMARY KEY (id)," +
                "    FOREIGN KEY (play_result_id) REFERENCES play_result (id) ON DELETE CASCADE" +
                ");");
    }

    @AfterEach
    void deleteTable() {
        jdbcTemplate.execute("DROP TABLE car_result IF EXISTS");
    }

    @Test
    void key() {
        PlayResult playResult = PlayResult.of(10, "juno", Timestamp.valueOf(LocalDateTime.now()));
        Integer playResultId = playResultDao.save(playResult);
        CarResult carResult = CarResult.of(playResultId, "juno", 3);
        int carId = carResultDao.save(carResult);
        CarResult result = carResultDao.findById(carId);
        System.out.println(result);
        assertThat(result).isNotNull();
    }
}
