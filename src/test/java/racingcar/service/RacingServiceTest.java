package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.CarResponse;
import racingcar.controller.TrackResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingServiceTest {

    @Autowired
    RacingService racingService;

    @Test
    @DisplayName("자동차 게임이 정상적으로 작동한다.")
    void playSuccess() {
        String names = "그레이,호이,로건";
        String trialTimes = "10";

        final TrackResponse trackResponse = racingService.play(names, trialTimes);
        final List<CarResponse> cars = trackResponse.getRacingCars();

        assertThat(cars.size()).isEqualTo(names.split(",").length);
    }
}
