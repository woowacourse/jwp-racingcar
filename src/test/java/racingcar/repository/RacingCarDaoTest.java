package racingcar.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.entity.RacingCar;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JdbcTest
@Transactional(propagation= Propagation.NOT_SUPPORTED)
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
        jdbcTemplate.execute("ALTER TABLE racing_cars ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE results ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");

//        jdbcTemplate.execute("DELETE FROM racing_cars");
//        jdbcTemplate.execute("DELETE FROM results");
//        jdbcTemplate.execute("ALTER TABLE racing_cars ALTER COLUMN id RESTART WITH 1");
//        jdbcTemplate.execute("ALTER TABLE results ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    @DisplayName("특정 result_id의 데이터를 모두 제대로 반환하는지 확인")
    void findBy() {
        List<RacingCar> racingCars = racingCarDao.findBy(1L);

        assertThat(racingCars).hasSize(4);
    }
}
