package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.web.RacingGameWebDao;
import racingcar.entity.GameEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@JdbcTest
public class RacingGameWebDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingGameWebDao racingGameWebDao;

    @BeforeEach
    void setUp() {
        racingGameWebDao = new RacingGameWebDao(jdbcTemplate);
    }

    @Test
    @DisplayName("racing_game 테이블에 game 저장")
    void saveGame() {
        GameEntity gameEntity = new GameEntity(1, 10, LocalDateTime.now());

        assertDoesNotThrow(() -> racingGameWebDao.saveGame(gameEntity));
    }

    @Test
    @DisplayName("id로 저장된 Game 객체 조회")
    void getRacingGameById() {
        int id = 1;
        int count = 10;
        int savedGameId = racingGameWebDao.saveGame(new GameEntity(id, count, LocalDateTime.now()));
        GameEntity racingGameById = racingGameWebDao.getRacingGameById(savedGameId);

        assertEquals(count, racingGameById.getCount());
    }

}
