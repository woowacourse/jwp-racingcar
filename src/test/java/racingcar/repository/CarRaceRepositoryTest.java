package racingcar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.RaceResult;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;
import racingcar.mock.MockNumberGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CarRaceRepositoryTest {

    private static final Cars cars = Cars.create("test1,test2", new MockNumberGenerator());
    private static final int trialCount = 10;
    private static final String winners = "test2";

    @Autowired
    private CarRaceRepositoryImpl carRaceRepository;

    @Test
    @DisplayName("레이싱 결과를 저장한다.")
    public void testSave() {
        //given
        final RaceResult raceResult = new RaceResult(trialCount, winners, cars);

        //when
        carRaceRepository.save(raceResult);

        //then
        final List<RaceEntity> raceEntities = carRaceRepository.findRaceEntities();
        assertThat(raceEntities.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("레이싱 결과를 조회한다.")
    public void testFindRaceEntities() {
        //given
        final RaceResult raceResult = new RaceResult(trialCount, winners, cars);
        carRaceRepository.save(raceResult);

        //when
        final List<RaceEntity> raceEntities = carRaceRepository.findRaceEntities();

        //then
        assertThat(raceEntities.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("자동차 저장 결과를 조회한다.")
    public void testFindCarEntities() {
        //given
        final RaceResult raceResult = new RaceResult(trialCount, winners, cars);
        carRaceRepository.save(raceResult);
        final List<RaceEntity> raceEntities = carRaceRepository.findRaceEntities();

        //when
        final List<CarEntity> carEntities = carRaceRepository.findCarEntities(
                raceEntities.get(0).getId());

        //then
        assertThat(carEntities.size()).isEqualTo(2);
    }
}
