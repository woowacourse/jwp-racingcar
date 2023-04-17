package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

@JdbcTest
class RacingCarDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingCarDao racingCarDao;

    @BeforeEach
    void setUp() {
        racingCarDao = new RacingCarDaoImpl(jdbcTemplate);
    }

    @DisplayName("횟수와 우승자를 저장한다")
    @Test
    void saveWinners() {
        //given
        final String winners = "a, b";
        final int count = 5;

        //when
        racingCarDao.saveWinners(count, winners);

        //then
        assertThat(countRowsInTableWhere(jdbcTemplate, "play_result", "winners='" + winners + "'"))
                .isEqualTo(1);
    }

    @DisplayName("자동차들을 저장한다")
    @Test
    void saveCars() {
        //given
        final List<Car> cars = List.of(new Car("a"), new Car("b"));

        final String winners = "a, b";
        final int count = 5;
        final long resultId = racingCarDao.saveWinners(count, winners);

        //when
        racingCarDao.saveCars(resultId, cars);

        //then
        assertThat(countRowsInTable(jdbcTemplate, "car")).isEqualTo(2);
    }

    @DisplayName("모든 우승자를 반환한다")
    @Test
    void findAllResult() {
        //given
        final String winners = "a, b";
        final int count = 5;

        //when
        racingCarDao.saveWinners(count, winners);
        racingCarDao.saveWinners(count, winners);
        racingCarDao.saveWinners(count, winners);

        //then
        assertThat(racingCarDao.findAllResult()).hasSize(3);
    }

    @DisplayName("result_id를 받아서 해당하는 자동차들을 반환한다")
    @Test
    void findCarsByResultId() {
        //given
        final List<Car> cars = List.of(new Car("a"), new Car("b"));
        final String winners = "a, b";
        final int count = 5;

        //when
        final long id = racingCarDao.saveWinners(count, winners);
        racingCarDao.saveCars(id, cars);

        //then
        assertThat(racingCarDao.findCarsByResultId(id))
                .map(Car::getName)
                .containsExactly("a", "b");
    }
}
