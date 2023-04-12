package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.utils.TestNumberGenerator;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CarDaoTest {

    @Autowired
    private CarDao carDao;

    @Autowired
    private GameDao gameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        final String sql = "insert into game (trial, winners) values (?,?)";
        jdbcTemplate.update(sql, 1, "car1");
    }

    @Test
    void 입력받은_자동차를_전부_저장한다() {
        // given
        final Cars cars = new Cars(List.of("car1", "car2"));
        NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3));
        cars.race(numberGenerator);
        final int gameId = gameDao.save(3, "car1");

        // when
        carDao.saveAll(gameId, cars.getCars());

        // then
        final String sql = "select count(*) from game";
        final int count = jdbcTemplate.queryForObject(sql, Integer.class);
        assertThat(count).isEqualTo(2);
    }
}
