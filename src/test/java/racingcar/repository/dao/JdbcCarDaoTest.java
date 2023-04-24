package racingcar.repository.dao;

import static org.assertj.core.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import racingcar.repository.entity.CarEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JdbcCarDaoTest {

    @Autowired
    private DataSource dataSource;

    private CarDao carDao;

    @BeforeEach
    void setUp() {
        carDao = new JdbcCarDao(dataSource);
    }

    @Test
    void save_메서드로_car를_저장한다() {
        final Long dummyGameId = 1L;
        final Long dummyPlayerId = 1L;
        final CarEntity carEntity = new CarEntity(dummyGameId, dummyPlayerId, 5);

        long carId = carDao.save(carEntity);

        assertThat(carId).isGreaterThan(0L);
    }
}
