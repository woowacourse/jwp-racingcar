package racingcar.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.dao.entity.RaceResultEntity;

class ConsoleRaceResultDaoTest {


    private static final int trialCount = 10;
    private static final String winners = "test1,test2";
    private static final List<Car> cars = List.of(Car.create("test1"), Car.create("test2"));

    private RaceResultDao raceResultDao;

    @Test
    @DisplayName("레이싱 결과를 저장한다.")
    public void testSave() {
        // given
        raceResultDao = new ConsoleRaceResultDao(new HashMap<>());

        //when
        final Long savedId = raceResultDao.save(trialCount, winners);

        //then
        assertThat(savedId).isNotNull();
    }

    @Test
    @DisplayName("모든 레이싱 결과를 가져온다.")
    public void testFindAll() {
        //given
        raceResultDao = new ConsoleRaceResultDao(new HashMap<>());
        raceResultDao.save(1, "test1");
        raceResultDao.save(2, "test2");

        //when
        final List<RaceResultEntity> result = raceResultDao.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
