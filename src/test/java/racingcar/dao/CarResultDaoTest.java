package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.CarResult;
import racingcar.domain.PlayResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CarResultDaoTest {
    private final JdbcTemplate jdbcTemplate;
    private PlayResultDao playResultDao;

    private CarResultDao carResultDao;

    @Autowired
    public CarResultDaoTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        playResultDao = new PlayResultDao(jdbcTemplate);
        carResultDao = new CarResultDao(jdbcTemplate);
    }

    @Test
    void saveCarResult() {
        PlayResult playResult = PlayResult.of(10, "juno", Timestamp.valueOf(LocalDateTime.now()));
        Integer playResultId = playResultDao.save(playResult);
        CarResult carResult = CarResult.of(playResultId, "juno", 3);
        int carId = carResultDao.save(carResult);
        CarResult result = carResultDao.findById(carId);
        assertThat(result).isNotNull();
    }
}
