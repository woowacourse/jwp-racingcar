package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import racingcar.service.CarEntity;
import racingcar.service.GameEntity;

@SpringBootTest
@Transactional
class JdbcCarDaoTest {

    @Autowired
    JdbcGameDao jdbcGameDao;

    @Autowired
    JdbcCarDao jdbcCarDao;

    private GameEntity gameEntity;

    @BeforeEach
    void initialize() {
        gameEntity = jdbcGameDao.insertRacingResult(new GameEntity("name1,name2", 3));
    }

    @Test
    @DisplayName("Car 1건이 제대로 db에 insert되는지 확인")
    void insertCarTest() {
        // given
        CarEntity carEntity = new CarEntity(gameEntity.getId(), "name", 10);
        jdbcCarDao.insertCar(carEntity);
        List<CarEntity> carEntities = jdbcCarDao.selectCarsByGameId(gameEntity.getId());

        // when
        CarEntity findedCarEntity = carEntities.get(0);

        // then
        assertThat(findedCarEntity.getName()).isEqualTo(carEntity.getName());
    }

    @Test
    @DisplayName("Car를 두 번 insert했을 때 모든 Car를 제대로 select하는 확인")
    void selectCarsByGameIdTest() {
        // given
        CarEntity carEntity1 = new CarEntity(gameEntity.getId(), "name1", 10);
        CarEntity carEntity2 = new CarEntity(gameEntity.getId(), "name2", 10);
        jdbcCarDao.insertCar(carEntity1);
        jdbcCarDao.insertCar(carEntity2);

        // when
        List<CarEntity> carEntities = jdbcCarDao.selectCarsByGameId(gameEntity.getId());

        // then
        assertThat(carEntities.size()).isEqualTo(2);

        assertThat(carEntities.get(0).getName()).isEqualTo(carEntity1.getName());
        assertThat(carEntities.get(1).getName()).isEqualTo(carEntity2.getName());

        assertThat(carEntities.get(0).getId()).isEqualTo(carEntity1.getId());
        assertThat(carEntities.get(1).getId()).isEqualTo(carEntity2.getId());
    }

}
