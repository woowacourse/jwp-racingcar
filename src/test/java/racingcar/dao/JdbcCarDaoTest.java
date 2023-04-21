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
import racingcar.service.RacingResult;

@SpringBootTest
@Transactional
class JdbcCarDaoTest {

    @Autowired
    JdbcGameDao jdbcGameDao;

    @Autowired
    JdbcCarDao jdbcCarDao;

    private RacingResult racingResult;

    @BeforeEach
    void initialize() {
        racingResult = jdbcGameDao.insertRacingResult(new RacingResult("name1,name2", 3));
    }

    @Test
    @DisplayName("playerResult 1건이 제대로 db에 insert되는지 확인")
    void insertPlayer() {
        // given
        CarEntity carEntity = new CarEntity(racingResult.getId(), "name", 10);
        jdbcCarDao.insertPlayer(carEntity);
        List<CarEntity> carEntities = jdbcCarDao.selectPlayerResultByRacingResultId(2);

        // when
        CarEntity findedCarEntity = carEntities.get(0);

        // then
        assertThat(findedCarEntity.getName()).isEqualTo(carEntity.getName());
    }

    @Test
    @DisplayName("playerResult를 두 번 insert했을 때 모든 playerResult를 제대로 select하는 확인")
    void selectPlayerResultByPlayResultIdTest() {
        // given
        CarEntity carEntity1 = new CarEntity(racingResult.getId(), "name1", 10);
        CarEntity carEntity2 = new CarEntity(racingResult.getId(), "name2", 10);
        jdbcCarDao.insertPlayer(carEntity1);
        jdbcCarDao.insertPlayer(carEntity2);

        // when
        List<CarEntity> carEntities = jdbcCarDao.selectPlayerResultByRacingResultId(racingResult.getId());

        // then
        assertThat(carEntities.size()).isEqualTo(2);
        assertThat(carEntities.containsAll(List.of(carEntity1, carEntity2))).isTrue();
    }

}
