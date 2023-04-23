package racingcar.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.dto.CarResponse;
import racingcar.controller.dto.TrackRequest;
import racingcar.controller.dto.TrackResponse;
import racingcar.mapper.TrackRequestMapper;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingServiceTest {

    @Autowired
    private RacingService racingService;

    @Test
    @DisplayName("자동차 게임이 정상적으로 작동한다.")
    void playSuccess() {
        // given
        final TrackRequest trackRequest = TrackRequestMapper.of("gray,hoy,logan", "10");
        final int expected = trackRequest.getNames().split(",").length;

        // when
        final TrackResponse trackResponse = racingService.play(trackRequest);
        final List<CarResponse> carResponses = trackResponse.getRacingCars();
        final int actual = carResponses.size();

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
