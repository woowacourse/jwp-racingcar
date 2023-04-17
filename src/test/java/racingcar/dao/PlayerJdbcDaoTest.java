package racingcar.dao;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.entity.Game;
import racingcar.entity.Player;
import racingcar.utils.TestNumberGenerator;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlayerJdbcDaoTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PlayerDao playerDao;
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        playerDao = new PlayerJdbcDao(dataSource);
        gameDao = new GameJdbcDao(dataSource);
    }

    @Test
    void 입력받은_자동차를_전부_저장한다() {
        // given
        final Cars cars = new Cars(List.of("car1", "car2"));
        NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3));
        cars.race(numberGenerator);

        Game game = Game.of(1);
        final int gameId = gameDao.save(game);

        List<Player> players = cars.getCars().stream()
                .map(car -> Player.of(car,true ,gameId))
                .collect(Collectors.toList());
        // when
        playerDao.saveAll(players);

        // then
        final String sql = "select count(*) from player";
        final int count = jdbcTemplate.queryForObject(sql, Integer.class);
        assertThat(count).isEqualTo(2);
    }
}
