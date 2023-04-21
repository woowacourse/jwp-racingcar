package racingcar.repository.dao;

import static org.assertj.core.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import racingcar.repository.entity.GameEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JdbcGameDaoTest {

    @Autowired
    private DataSource dataSource;

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new JdbcGameDao(dataSource);
    }

    @Test
    void save_메서드로_game을_저장한다() {
        final GameEntity gameEntity = new GameEntity(5);

        long gameId = gameDao.save(gameEntity);

        assertThat(gameId).isGreaterThan(0L);
    }
}
