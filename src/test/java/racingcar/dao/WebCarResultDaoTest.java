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
import java.util.ArrayList;
import java.util.List;

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
    void saveAndFindById() {
        PlayResult playResult = PlayResult.of(10, "juno", Timestamp.valueOf(LocalDateTime.now()));
        Integer playResultId = webPlayResultDao.save(playResult);
        CarResult carResult = CarResult.of(playResultId, "juno", 3);
        int carId = webCarResultDao.save(carResult);
        CarResult result = webCarResultDao.findById(carId);
        assertThat(result).isNotNull();
    }

    @Test
    void findAllByPlayResultId() {
        PlayResult playResult = PlayResult.of(10, "juno,hongo", Timestamp.valueOf(LocalDateTime.now()));
        int playResultId = webPlayResultDao.save(playResult);
        CarResult carResult1 = CarResult.of(playResultId, "juno", 3);
        CarResult carResult2 = CarResult.of(playResultId, "hongo", 3);
        int carResult1Id = webCarResultDao.save(carResult1);
        int carResult2Id = webCarResultDao.save(carResult2);

        List<CarResult> expectedCarResults = new ArrayList<>();
        expectedCarResults.add(CarResult.of(carResult1Id, playResultId, "juno", 3));
        expectedCarResults.add(CarResult.of(carResult2Id, playResultId, "hongo", 3));
        List<CarResult> carResults = webCarResultDao.findAllByPlayResultId(playResultId);
        assertThat(carResults).isEqualTo(expectedCarResults);
    }
}
