package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void clear() {
        String sql = "delete from PLAYER";
        jdbcTemplate.update(sql);
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

    @Test
    void 저장된_플레이어_정보를_불러온다() {
        int gameId1 = gameDao.insert(Game.of(List.of(new Car("car1", numberGenerator)), 4)).intValue();
        List<Player> players1 = List.of(
                Player.of(new Car("car1", numberGenerator), gameId1),
                Player.of(new Car("car2", numberGenerator), gameId1),
                Player.of(new Car("car3", numberGenerator), gameId1)
        );

        int gameId2 = gameDao.insert(Game.of(List.of(new Car("car4", numberGenerator)), 4)).intValue();
        List<Player> players2 = List.of(
                Player.of(new Car("car4", numberGenerator), gameId2),
                Player.of(new Car("car5", numberGenerator), gameId2)
        );
        playerDao.insert(players1);
        playerDao.insert(players2);

        List<Player> players = playerDao.findAll();

        assertAll(
                () -> assertThat(players).hasSize(5),
                () -> assertThat(players).extracting("name")
                        .containsExactlyInAnyOrder("car1", "car2", "car3", "car4", "car5"),
                () -> assertThat(players).extracting("gameId")
                        .containsExactlyInAnyOrder(gameId1, gameId1, gameId1, gameId2, gameId2)
        );
    }
}
