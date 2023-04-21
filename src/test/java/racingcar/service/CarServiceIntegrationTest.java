package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.CarDao;
import racingcar.domain.RacingCars;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("CarService Integration Test")
@SpringBootTest
class CarServiceIntegrationTest {

    @Autowired
    private CarDao carDao;

    @Autowired
    private CarService carService;

    @Test
    @DisplayName("registerCars() : 자동차들을 저장합니다.")
    void test_registerCars() throws Exception {
        //given
        final RacingCars racingCars = RacingCars.makeCars("a,b,c");
        final int savedRaceResultId = 3;

        //when & then
        assertDoesNotThrow(() -> carService.registerCars(racingCars, savedRaceResultId));
    }
}
