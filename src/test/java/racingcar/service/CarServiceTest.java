package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dao.CarDao;
import racingcar.domain.RacingCars;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("CarService Unit Test")
class CarServiceTest {

    private CarService carService;

    private CarDao carDao;

    @BeforeEach
    void init() {
        carDao = mock(CarDao.class);

        carService = new CarService(carDao);
    }

    @Test
    @DisplayName("registerCars() : 자동차들을 저장합니다.")
    void test_registerCars() throws Exception {
        //given
        final RacingCars racingCars = RacingCars.makeCars("a,b,c");
        final int savedRaceResultId = 1;

        //when
        carService.registerCars(racingCars, savedRaceResultId);

        //then
        verify(carDao, times(1)).save(any());
    }
}
