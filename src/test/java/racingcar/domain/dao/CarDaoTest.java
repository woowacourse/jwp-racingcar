package racingcar.domain.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.dao.entity.CarEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class CarDaoTest {

    @Autowired
    private CarDao carDao;
    @Autowired
    private RaceResultDao raceResultDao;

    @Test
    @DisplayName("차들의 정보를 저장한다")
    public void testSaveAll() {
        //given
        final int trialCount = 10;
        final String winners = "test1,test2";
        final Long raceResultId = raceResultDao.save(trialCount, winners);
        final Car car1 = Car.create("test1");
        final Car car2 = Car.create("test2");
        final List<Car> cars = List.of(car1, car2);

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
        final int trialCount = 10;
        final String winners = "test1,test2";
        final Long raceResultId = raceResultDao.save(trialCount, winners);
        final Car car1 = Car.create("test1");
        final Car car2 = Car.create("test2");
        final List<Car> cars = List.of(car1, car2);
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
