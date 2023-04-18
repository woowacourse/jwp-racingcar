package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Name;

@SpringBootTest
@Transactional
public class WinnersDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private WinnersDao winnersDao;
    private GameLogDao gameLogDao;

    @BeforeEach
    void setUp() {
        winnersDao = new WinnersDao(jdbcTemplate);
        gameLogDao = new GameLogDao(jdbcTemplate);
    }

    @Test
    void insert() {
        assertThatNoException().isThrownBy(() -> winnersDao.insert(5, "달리"));
    }

    @Test
    void findWinners() {
        gameLogDao.insert(3, "달리", 4);
        gameLogDao.insert(3, "디노", 4);
        gameLogDao.insert(3, "저문", 2);

        winnersDao.insert(3, "달리");
        winnersDao.insert(3, "디노");

        assertThat(winnersDao.find(3)).isEqualTo(List.of(new Car(new Name("달리"), 4), new Car(new Name("디노"), 4)));
    }

    @Test
    void findAnotherGameWinners() {
        gameLogDao.insert(5, "메리", 7);
        gameLogDao.insert(5, "밀리", 4);

        winnersDao.insert(5, "메리");

        assertThat(winnersDao.find(5)).isEqualTo(List.of(new Car(new Name("메리"), 7)));
    }
}
