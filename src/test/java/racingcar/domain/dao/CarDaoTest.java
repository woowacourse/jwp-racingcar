package racingcar.domain.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.dao.entity.CarEntity;

@SpringBootTest
@Transactional
class CarDaoTest {

    private static final List<Car> cars = List.of(Car.create("test1"), Car.create("test2"));
    private static final int trialCount = 10;
    private static final String winners = "test1,test2";

    @Autowired
    private H2CarDao carDao;
    @Autowired
    private H2RaceResultResultDao raceResultDao;

    @Test
    @DisplayName("차들의 정보를 저장한다")
    public void testSaveAll() {
        //given
        final Long raceResultId = raceResultDao.save(trialCount, winners);

        //when
        carDao.saveAll(raceResultId, cars);

        // then
        final List<CarEntity> result = carDao.findAll(raceResultId);
        final List<String> resultNames = result.stream().map(CarEntity::getName)
            .collect(Collectors.toUnmodifiableList());

        // then
        assertThat(resultNames)
            .isEqualTo(List.of("test1", "test2"));
    }

    @Test
    @DisplayName("모든 차 리스트를 반환한다")
    public void findAll() {
        // given
        final Long raceResultId = raceResultDao.save(trialCount, winners);
        carDao.saveAll(raceResultId, cars);

        // when
        final List<CarEntity> result = carDao.findAll(raceResultId);
        final List<String> resultNames = result.stream().map(CarEntity::getName)
            .collect(Collectors.toUnmodifiableList());

        // then
        assertThat(resultNames)
            .isEqualTo(List.of("test1", "test2"));
    }
}
