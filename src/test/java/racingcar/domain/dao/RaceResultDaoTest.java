package racingcar.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceResultEntity;

@SpringBootTest
@Transactional
class RaceResultDaoTest {

    private static final int trialCount = 10;
    private static final String winners = "test1,test2";
    private static final List<Car> cars = List.of(Car.create("test1"), Car.create("test2"));

    @Autowired
    private H2RaceResultDao raceResultDao;
    @Autowired
    private H2CarDao carDao;

    @Test
    @DisplayName("레이싱 결과를 저장한다.")
    public void testSave() {
        //when
        final Long savedId = raceResultDao.save(trialCount, winners);

        //then
        assertThat(savedId).isNotNull();
    }

    @Test
    @DisplayName("모든 레이싱 결과를 가져온다.")
    public void testFindAll() {
        //given
        final Long raceResultId = raceResultDao.save(trialCount, winners);
        final List<CarEntity> carEntities = cars.stream()
            .map(car -> new CarEntity(null, car.getName(), car.getPosition()))
            .collect(Collectors.toUnmodifiableList());
        carDao.saveAll(raceResultId, carEntities);

        //when
        final List<RaceResultEntity> result = raceResultDao.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
    }
}
