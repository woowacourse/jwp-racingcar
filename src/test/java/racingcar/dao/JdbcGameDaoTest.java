package racingcar.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import racingcar.entity.GameEntity;

@JdbcTest
class JdbcGameDaoTest {

    private final JdbcGameDao jdbcGameDao;

    @Autowired
    public JdbcGameDaoTest(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcGameDao = new JdbcGameDao(dataSource, namedParameterJdbcTemplate);
    }

    @Test
    @DisplayName("game 1건이 제대로 insert되었는지 확인")
    void insertGameTest() {
        // given
        GameEntity gameEntity = new GameEntity("name1,name2", 3);
        jdbcGameDao.insertGame(gameEntity);
        List<GameEntity> gameEntities = jdbcGameDao.selectAllGames();

        // when
        GameEntity findGame = gameEntities.get(0);

        // then
        assertThat(findGame.getId()).isEqualTo(gameEntity.getId());
        assertThat(findGame.getWinners()).isEqualTo(gameEntity.getWinners());
        assertThat(findGame.getTrial()).isEqualTo(gameEntity.getTrial());
    }

    @Test
    @DisplayName("Game을 두 번 insert했을 때 모든 Game을 제대로 select하는 확인")
    void selectAllGames() {
        // given
        GameEntity gameEntity1 = new GameEntity("name1,name2", 3);
        GameEntity gameEntity2 = new GameEntity("name3,name4", 4);

        jdbcGameDao.insertGame(gameEntity1);
        jdbcGameDao.insertGame(gameEntity2);

        // when
        List<GameEntity> gameEntities = jdbcGameDao.selectAllGames();

        // then
        assertThat(gameEntities.size()).isEqualTo(2);
        assertThat(gameEntities).extracting("id", "winners")
            .contains(tuple(gameEntity1.getId(), gameEntity1.getWinners()),
                tuple(gameEntity2.getId(), gameEntity2.getWinners()));
    }
}
