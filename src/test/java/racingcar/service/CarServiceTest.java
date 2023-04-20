package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dao.car.CarDao;
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
    void test_registerCars2() throws Exception {
        //given
        final RacingCars racingCars = RacingCars.makeCars("a,b,c");
        final int savedId = 1;

        //when
        carService.registerCars(racingCars, savedId);

        //then
        verify(carDao, times(1)).save(any());
    }
}
