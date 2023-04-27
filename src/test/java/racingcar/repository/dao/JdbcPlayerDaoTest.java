package racingcar.repository.dao;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import racingcar.repository.entity.PlayerEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JdbcPlayerDaoTest {

    @Autowired
    private DataSource dataSource;

    private PlayerDao playerDao;

    @BeforeEach
    void setUp() {
        playerDao = new JdbcPlayerDao(dataSource);
    }

    @Test
    void save_메서드로_player를_저장한다() {
        final PlayerEntity playerEntity = new PlayerEntity("modi");
        long playerId = playerDao.save(playerEntity);

        assertThat(playerId).isGreaterThan(1L);
    }

    @Test
    void findByName_메서드로_player를_검색한다() {
        final PlayerEntity modiEntity = new PlayerEntity("modi");
        long playerId = playerDao.save(modiEntity);

        final PlayerEntity findEntity = playerDao.findByName("modi");

        assertAll(
            () -> assertEquals(playerId, findEntity.getId()),
            () -> assertEquals(modiEntity.getName(), findEntity.getName())
        );
    }
}
