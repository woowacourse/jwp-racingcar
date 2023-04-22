package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.RacingGame;
import racingcar.util.RandomNumberGenerator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("CarService Integration Test")
@SpringBootTest
class CarServiceIntegrationTest {

    @Autowired
    private CarService carService;

    @Test
    @DisplayName("registerCars() : 자동차들을 저장합니다.")
    void test_registerCars() throws Exception {
        //given
        final int trialCount = 2;
        final RacingGame racingGame =
                RacingGame.readyToRacingGame("a,b,c",
                                             new RandomNumberGenerator(),
                                             trialCount);
        final Long savedRaceResultId = 3L;

        //when & then
        assertDoesNotThrow(() -> carService.registerCars(racingGame, savedRaceResultId));
    }
}
