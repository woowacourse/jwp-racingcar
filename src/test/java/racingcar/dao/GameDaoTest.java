package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class GameDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private GameDao jdbcGameDao;

    @BeforeEach
    void setUp() {
        jdbcGameDao = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    void insertGameTest() {
        Assertions.assertThatNoException().isThrownBy(() -> jdbcGameDao.insert("jena,odo", 1));
    }

    @Test
    void findAllIdsTest() {
        // given
        jdbcGameDao.insert("jena1", 2);
        jdbcGameDao.insert("odo1", 3);

        // when
        List<Integer> gameIds = jdbcGameDao.findAllIds();

        // then
        assertThat(gameIds.size()).isEqualTo(2);
    }

    @Test
    void findWinnersTest() {
        // given
        int gameId = jdbcGameDao.insert("jena3", 3);

        // when
        String winners = jdbcGameDao.findWinners(gameId);

        // then
        assertThat(winners).isEqualTo("jena3");

    }
}
