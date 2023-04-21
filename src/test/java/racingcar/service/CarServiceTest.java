package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dao.CarDao;
import racingcar.domain.RacingCars;
import racingcar.entity.CarEntity;
import racingcar.service.mapper.CarMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("CarService Unit Test")
class CarServiceTest {

    private CarService carService;

    private CarDao carDao;
    private CarMapper carMapper;

    @BeforeEach
    void init() {
        carDao = mock(CarDao.class);
        carMapper = mock(CarMapper.class);

        carService = new CarService(carDao, carMapper);
    }

    @Test
    @DisplayName("registerCars() : 자동차들을 저장합니다.")
    void test_registerCars() throws Exception {
        //given
        final RacingCars racingCars = RacingCars.makeCars("a,b,c");
        final int savedRaceResultId = 1;

        final List<CarEntity> carEntities = List.of(
                new CarEntity("a", 3, 1, LocalDateTime.now()),
                new CarEntity("b", 3, 1, LocalDateTime.now()),
                new CarEntity("c", 3, 1, LocalDateTime.now())
        );

        //when
        when(carMapper.mapToCarEntitiesFrom(racingCars, savedRaceResultId))
                .thenReturn(carEntities);

        carService.registerCars(racingCars, savedRaceResultId);

        //then
        verify(carDao, times(1)).save(any());
    }
}
