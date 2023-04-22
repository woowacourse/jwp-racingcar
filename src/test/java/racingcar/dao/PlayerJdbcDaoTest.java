package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class PlayerJdbcDaoTest {

    private PlayerDao playerJdbcDao;
    private PlayResultDao playResultDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        playerJdbcDao = new PlayerJdbcDao(jdbcTemplate);
        playResultDao = new PlayResultJdbcDao(jdbcTemplate);
    }

    @Test
    public void insert() {
        final long playResultId = playResultDao.insert();
        List<Player> players = List.of(
                new Player(playResultId, "무민", 8, true)
        );

        playerJdbcDao.insertAll(players);

        assertEquals(1, playerJdbcDao.findAllPlayer(playResultId).size());
    }

    @Test
    public void findAllPlayer() {
        final long playResultId = playResultDao.insert();
        List<Player> players = List.of(
                new Player(playResultId, "무민", 8, true),
                new Player(playResultId, "준팍", 6, false)
        );
        playerJdbcDao.insertAll(players);

        final List<Player> allPlayer = playerJdbcDao.findAllPlayer(playResultId);

        final Player player = allPlayer.get(0);
        assertAll(
                () -> assertEquals(2, allPlayer.size()),
                () -> assertEquals("무민", player.getName()),
                () -> assertEquals(8, player.getPosition())
        );
    }
}