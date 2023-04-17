package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.GameEntity;

import java.util.List;

@JdbcTest
class RacingGameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameDao(jdbcTemplate);
    }

    @Test
    void saveGame() {
        GameEntity gameEntity = generateGameEntity();

        Assertions.assertDoesNotThrow(() -> racingGameDao.save(gameEntity));
    }

    @Test
    void findAll() {
        for (int i = 0; i < 2; i++) {
            racingGameDao.save(generateGameEntity());
        }

        List<GameEntity> resultGame = racingGameDao.findAll();

        Assertions.assertEquals(resultGame.size(), 2);
    }

    public static GameEntity generateGameEntity() {
        return new GameEntity.Builder()
                .count(10)
                .winners("메리")
                .build();
    }

}
