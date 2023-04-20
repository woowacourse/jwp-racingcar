package racingcar.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.dao.entity.CarEntity;

class ConsoleCarDaoTest {

    private CarDao carDao;

    @Test
    @DisplayName("모든 차 정보를 저장한다.")
    public void testSaveAll() {
        //given
        carDao = new ConsoleCarDao(new HashMap<>());
        final Long raceResultId = 1L;
        final CarEntity carEntity1 = new CarEntity(1L, "test", 1);
        final CarEntity carEntity2 = new CarEntity(2L, "test", 2);
        final List<CarEntity> carEntities = List.of(carEntity1, carEntity2);

        //when
        carDao.saveAll(raceResultId, carEntities);

        //then
        final List<CarEntity> result = carDao.findAll(raceResultId);
        for (int index = 0; index < result.size(); index++) {
            assertThat(result.get(index)).isEqualTo(carEntities.get(index));
        }
    }

    @Test
    @DisplayName("모든 차 정보를 반환한다.")
    public void testFindAll() {
        //given
        carDao = new ConsoleCarDao(new HashMap<>());
        final Long raceResultId = 1L;
        final CarEntity carEntity1 = new CarEntity(1L, "test", 1);
        final CarEntity carEntity2 = new CarEntity(2L, "test", 2);
        final List<CarEntity> carEntities = List.of(carEntity1, carEntity2);
        carDao.saveAll(raceResultId, carEntities);

        //when
        final List<CarEntity> result = carDao.findAll(raceResultId);

        //then
        for (int index = 0; index < result.size(); index++) {
            assertThat(result.get(index)).isEqualTo(carEntities.get(index));
        }
    }
}
