package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.entity.Game;
import racingcar.entity.Player;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

@JdbcTest
class PlayerDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;

    private PlayerDao playerDao;

    private NumberGenerator numberGenerator;

    @BeforeEach
    void setUp() {
        playerDao = new PlayerDao(jdbcTemplate);
        gameDao = new GameDao(jdbcTemplate);
        numberGenerator = new RandomNumberGenerator();
    }

    @Test
    void 플레이어를_저장한다() {
        int gameId = gameDao.insert(Game.of(List.of(new Car("허브", numberGenerator)), 4)).intValue();

        List<Player> players = List.of(
                Player.of(new Car("허브", numberGenerator), gameId),
                Player.of(new Car("비버", numberGenerator), gameId),
                Player.of(new Car("애쉬", numberGenerator), gameId)
        );

        playerDao.insert(players);

        String sql = "select count(*) from PLAYER where game_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, gameId);
        assertThat(count).isEqualTo(3);
    }
}
