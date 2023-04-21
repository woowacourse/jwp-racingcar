package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.CarDao;
import racingcar.entity.CarEntity;

import java.time.LocalDateTime;
import java.util.List;

@JdbcTest
class CarDaoTest {

    CarDao carDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        carDao = new CarDao(jdbcTemplate);
    }

    @Test
    @DisplayName("CarEntity를 통해서 Car 를 저장할 수 있다.")
    void test_save() throws Exception {
        //given
        final List<CarEntity> carEntities =
                List.of(new CarEntity("a", 1, 3, LocalDateTime.now()),
                        new CarEntity("b", 2, 3, LocalDateTime.now())
                );

        //when
        Assertions.assertDoesNotThrow(() -> carDao.save(carEntities));
    }
}
