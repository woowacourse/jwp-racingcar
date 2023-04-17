package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

@JdbcTest
@DisplayName("CarJdbcDao 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CarJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        carDao = new CarJdbcDao(jdbcTemplate);
        gameDao = new GameJdbcDao(jdbcTemplate);
    }

    @Test
    void saveAll_메서드는_입력받은_플레이어를_전부_저장한다() {
        // given
        final Integer gameId = gameDao.saveAndGetId(new GameEntity(3)).get();
        final List<CarEntity> players = List.of(new CarEntity("car1", 1, true, gameId));

        // when
        carDao.saveAll(players);

        // then
        final String sql = "select count(*) from car";
        final int count = jdbcTemplate.queryForObject(sql, Integer.class);
        assertThat(count).isEqualTo(1);
    }
}
