package racingcar.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

@JdbcTest
class JdbcCarDaoTest {
    private final JdbcGameDao jdbcGameDao;
    private final JdbcCarDao jdbcCarDao;
    private GameEntity gameEntity;

    @Autowired
    public JdbcCarDaoTest(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcGameDao = new JdbcGameDao(dataSource, namedParameterJdbcTemplate);
        this.jdbcCarDao = new JdbcCarDao(dataSource, namedParameterJdbcTemplate);
    }

    @BeforeEach
    void initialize() {
        gameEntity = jdbcGameDao.insertGame(new GameEntity("name1,name2", 3));
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

        assertThat(carEntities).extracting("id", "name")
            .contains(tuple(carEntity1.getId(), carEntity1.getName()),
                tuple(carEntity2.getId(), carEntity2.getName()));
    }

}
