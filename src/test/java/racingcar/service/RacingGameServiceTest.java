package racingcar.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import racingcar.AlwaysMoveNumberGenerator;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Import(value = AlwaysMoveNumberGenerator.class)
@Transactional
@SpringBootTest
class RacingGameServiceTest {

    @Autowired
    RacingGameService racingGameService;

    @Test
    void playTest() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("박스터,엔초", 10);
        String winners = "박스터,엔초";

        RacingGameResponse play = racingGameService.play(racingGameRequest);

        assertThat(play.getWinners()).isEqualTo(winners);
        assertThat(play.getRacingCars()).hasSize(2);
    }
}
