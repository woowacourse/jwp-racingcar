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
class WebCarResultDaoTest {
    private final JdbcTemplate jdbcTemplate;
    private WebPlayResultDao webPlayResultDao;

    private WebCarResultDao webCarResultDao;

    @Autowired
    public WebCarResultDaoTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        webPlayResultDao = new WebPlayResultDao(jdbcTemplate);
        webCarResultDao = new WebCarResultDao(jdbcTemplate);
    }

    @Test
    void saveCarResult() {
        PlayResult playResult = PlayResult.of(10, "juno", Timestamp.valueOf(LocalDateTime.now()));
        Integer playResultId = webPlayResultDao.save(playResult);
        CarResult carResult = CarResult.of(playResultId, "juno", 3);
        int carId = webCarResultDao.save(carResult);
        CarResult result = webCarResultDao.findById(carId);
        assertThat(result).isNotNull();
    }
}
