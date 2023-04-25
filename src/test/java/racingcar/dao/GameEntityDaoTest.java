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
import racingcar.entity.GameEntity;

@JdbcTest
class GameEntityDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameJdbcDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameJdbcDao(jdbcTemplate);
    }

    @AfterEach
    void clear() {
        String sql = "delete from GAME";
        jdbcTemplate.update(sql);
    }

    @Test
    void 게임을_저장한다() {
        GameEntity gameEntity = GameEntity.from(10);

        Long id = gameDao.insert(gameEntity);

        assertThat(id).isPositive();
    }

    @Test
    void 저장된_게임_정보를_불러온다() {
        GameEntity gameEntity1 = GameEntity.from(5);
        GameEntity gameEntity2 = GameEntity.from(10);
        gameDao.insert(gameEntity1);
        gameDao.insert(gameEntity2);

        List<GameEntity> gameEntities = gameDao.findAll();

        assertAll(
                () -> assertThat(gameEntities).hasSize(2),
                () -> assertThat(gameEntities).extracting("trialCount")
                        .containsExactlyInAnyOrder(5, 10)
        );
    }
}
