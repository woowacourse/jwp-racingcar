package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.service.GameEntity;

class InMemoryGameDaoTest {
    private final InMemoryGameDao inMemoryGameDao = new InMemoryGameDao();

    @BeforeEach
    void clear() {
        inMemoryGameDao.clearStore();
    }

    @Test
    @DisplayName("RacingResult 1건이 제대로 insert되었는지 확인")
    void insertRacingResultTest() {
        // given
        GameEntity gameEntity = new GameEntity("name1,name2", 3);
        inMemoryGameDao.insertRacingResult(gameEntity);
        List<GameEntity> gameEntities = inMemoryGameDao.selectAllResults();

        // when
        GameEntity findPlayResult = gameEntities.get(0);

        // then
        assertThat(findPlayResult.getId()).isEqualTo(gameEntity.getId());
        assertThat(findPlayResult.getWinners()).isEqualTo(gameEntity.getWinners());
        assertThat(findPlayResult.getTrialCount()).isEqualTo(gameEntity.getTrialCount());
    }

    @Test
    @DisplayName("RacingResult를 두 번 insert했을 때 모든 RacingResult를 제대로 select하는 확인")
    void selectAllResultsTest() {
        // given
        GameEntity gameEntity1 = new GameEntity("name1,name2", 3);
        GameEntity gameEntity2 = new GameEntity("name3,name4", 4);

        inMemoryGameDao.insertRacingResult(gameEntity1);
        inMemoryGameDao.insertRacingResult(gameEntity2);

        // when
        List<GameEntity> gameEntities = inMemoryGameDao.selectAllResults();
        System.out.println(gameEntities);

        // then
        assertThat(gameEntities.size()).isEqualTo(2);

        assertThat(gameEntities.get(0).getId()).isEqualTo(gameEntity1.getId());
        assertThat(gameEntities.get(1).getId()).isEqualTo(gameEntity2.getId());

        assertThat(gameEntities.get(0).getWinners()).isEqualTo(gameEntity1.getWinners());
        assertThat(gameEntities.get(1).getWinners()).isEqualTo(gameEntity2.getWinners());
    }

}
