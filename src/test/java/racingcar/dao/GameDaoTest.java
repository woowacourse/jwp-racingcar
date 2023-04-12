package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class GameDaoTest {

    private GameDao gameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao(jdbcTemplate.getDataSource());
    }

    @Test
    void 게임_저장_테스트() {
        long gameId1 = gameDao.save(3);
        long gameId2 = gameDao.save(5);

        Integer size = jdbcTemplate.queryForObject("SELECT count(*) FROM game", Integer.class);
        assertAll(
                () -> assertThat(gameId1).isEqualTo(1),
                () -> assertThat(gameId2).isEqualTo(2),
                () -> assertThat(size).isEqualTo(2)
        );
    }
}
