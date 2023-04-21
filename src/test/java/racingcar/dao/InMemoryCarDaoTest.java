package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.service.CarEntity;
import racingcar.service.GameEntity;

class InMemoryCarDaoTest {
    private final InMemoryGameDao inMemoryGameDao = new InMemoryGameDao();

    private final InMemoryCarDao inMemoryCarDao = new InMemoryCarDao();

    private GameEntity gameEntity;

    @BeforeEach
    void initialize() {
        inMemoryCarDao.clearStore();
        inMemoryGameDao.clearStore();
        gameEntity = inMemoryGameDao.insertRacingResult(new GameEntity("name1,name2", 3));
    }

    @Test
    @DisplayName("playerResult 1건이 제대로 db에 insert되는지 확인")
    void insertPlayer() {
        // given
        CarEntity carEntity = new CarEntity(gameEntity.getId(), "name", 10);
        inMemoryCarDao.insertCar(carEntity);
        List<CarEntity> carEntities = inMemoryCarDao.selectCarsByGameId(carEntity.getId());

        // when
        CarEntity findedCarEntity = carEntities.get(0);

        // then
        assertThat(findedCarEntity.getName()).isEqualTo(carEntity.getName());
    }

    @Test
    @DisplayName("playerResult를 두 번 insert했을 때 모든 playerResult를 제대로 select하는 확인")
    void selectPlayerResultByPlayResultIdTest() {
        // given
        CarEntity carEntity1 = new CarEntity(gameEntity.getId(), "name1", 10);
        CarEntity carEntity2 = new CarEntity(gameEntity.getId(), "name2", 10);
        inMemoryCarDao.insertCar(carEntity1);
        inMemoryCarDao.insertCar(carEntity2);

        // when
        List<CarEntity> carEntities = inMemoryCarDao.selectCarsByGameId(gameEntity.getId());

        // then
        assertThat(carEntities.size()).isEqualTo(2);

        assertThat(carEntities.get(0).getName()).isEqualTo(carEntity1.getName());
        assertThat(carEntities.get(1).getName()).isEqualTo(carEntity2.getName());

        assertThat(carEntities.get(0).getId()).isEqualTo(carEntity1.getId());
        assertThat(carEntities.get(1).getId()).isEqualTo(carEntity2.getId());
    }
}
