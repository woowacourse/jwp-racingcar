package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.TrackResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingServiceTest {

    @Autowired
    RacingService racingService;

    @Test
    @DisplayName("자동차 게임이 정상적으로 작동한다.")
    void playSuccess() {
        List<String> names = List.of("그레이", "호이", "로건");
        Integer trialTimes = 10;

        TrackResponse trackResponse = racingService.play(names, trialTimes);

        assertThat(trackResponse.getRacingCars()).hasSameSizeAs(names);
    }
}
