package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.entity.GameEntity;

class InMemoryGameDaoTest {
    private final InMemoryGameDao inMemoryGameDao = new InMemoryGameDao();

    @BeforeEach
    void clear() {
        inMemoryGameDao.clearStore();
    }

    @Test
    @DisplayName("game 1건이 제대로 insert되었는지 확인")
    void insertGameTest() {
        // given
        GameEntity gameEntity = new GameEntity("name1,name2", 3);
        inMemoryGameDao.insertGame(gameEntity);
        List<GameEntity> gameEntities = inMemoryGameDao.selectAllGames();

        // when
        GameEntity findGame = gameEntities.get(0);

        // then
        assertThat(findGame.getId()).isEqualTo(gameEntity.getId());
        assertThat(findGame.getWinners()).isEqualTo(gameEntity.getWinners());
        assertThat(findGame.getTrial()).isEqualTo(gameEntity.getTrial());
    }

    @Test
    @DisplayName("game을 두 번 insert했을 때 모든 game을 제대로 select하는 확인")
    void selectAllGamesTest() {
        // given
        GameEntity gameEntity1 = new GameEntity("name1,name2", 3);
        GameEntity gameEntity2 = new GameEntity("name3,name4", 4);

        inMemoryGameDao.insertGame(gameEntity1);
        inMemoryGameDao.insertGame(gameEntity2);

        // when
        List<GameEntity> gameEntities = inMemoryGameDao.selectAllGames();

        // then
        assertThat(gameEntities.size()).isEqualTo(2);

        assertThat(gameEntities.get(0).getId()).isEqualTo(gameEntity1.getId());
        assertThat(gameEntities.get(1).getId()).isEqualTo(gameEntity2.getId());

        assertThat(gameEntities.get(0).getWinners()).isEqualTo(gameEntity1.getWinners());
        assertThat(gameEntities.get(1).getWinners()).isEqualTo(gameEntity2.getWinners());
    }

}
