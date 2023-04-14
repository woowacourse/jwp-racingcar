package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@SpringBootTest
class RacingGameServiceTest {

    @Autowired
    RacingGameService racingGameService;

    @Test
    void playTest() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("박스터,엔초", 10);

        RacingGameResponse play = racingGameService.play(racingGameRequest);

        assertAll(
                () -> assertThat(play.getWinners()).isNotEmpty(),
                () -> assertThat(play.getRacingCars()).hasSize(2)
        );
    }
}
