package racingcar.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.dto.RacingCarDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JdbcTest
class RacingCarDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RacingCarDao racingCarDao;

    @BeforeEach
    void init() {
        ResultDao resultDao = new ResultDao(jdbcTemplate);
        resultDao.insert(5, "test1");
        resultDao.insert(7, "test6");

        racingCarDao = new RacingCarDao(jdbcTemplate);

        racingCarDao.insert(new Car(new Name("test1")), 1L);
        racingCarDao.insert(new Car(new Name("test2")), 1L);
        racingCarDao.insert(new Car(new Name("test3")), 1L);
        racingCarDao.insert(new Car(new Name("test4")), 1L);
        racingCarDao.insert(new Car(new Name("test5")), 2L);
        racingCarDao.insert(new Car(new Name("test6")), 2L);
    }

    @AfterEach
    void reset() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
        jdbcTemplate.update("TRUNCATE TABLE racing_cars");
        jdbcTemplate.update("TRUNCATE TABLE results");
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
    }

    @Test
    @DisplayName("특정 result_id의 데이터를 모두 제대로 반환하는지 확인")
    void findBy() {
        List<RacingCarDto> racingCars = racingCarDao.findBy(1L);

        assertThat(racingCars).hasSize(4);
    }
}
