package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.domain.Name;

@JdbcTest
public class WinnersDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private WinnersDao winnersDao;

    @BeforeEach
    void setUp() {
        winnersDao = new WinnersDao(jdbcTemplate);
    }

    @Test
    void insert() {
        assertThatNoException().isThrownBy(() -> winnersDao.insert(5, "달리"));
    }

    @Test
    void find() {
        winnersDao.insert(1, "달리");
        winnersDao.insert(1, "디노");
        System.out.println(winnersDao.find(1));
        assertThat(winnersDao.find(1)).isEqualTo(List.of(new Car(new Name("달리")), new Car(new Name("디노"))));
    }
}
