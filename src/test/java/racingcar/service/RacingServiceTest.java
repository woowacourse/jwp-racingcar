package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.RacingRequest;
import racingcar.dto.RacingResultDto;


class RacingServiceTest {

    @Test
    @DisplayName("플레이어가 한명일 때 GameResultDto를 반환한다.")
    void shouldReturnCorrectlyWhenInputOnePlayer() {
        RacingService racingService = new RacingService(new DummyRacingDao());
        RacingRequest racingRequest = new RacingRequest(
                "브리",
                3
        );

        RacingResultDto racingResultDto = racingService.playRacingGame(racingRequest);

        assertThat(racingResultDto.getRacingCars()).hasSize(1);
        assertThat(racingResultDto.getWinners()).hasSize(1);
        assertThat(racingResultDto.getWinners().get(0).getName()).isEqualTo("브리");
    }

    @Test
    @DisplayName("플레이어가 여려명일 때 GameResultDto를 반환한다.")
    void shouldReturnCorrectlyWhenInputManyPlayer() {
        RacingService racingService = new RacingService(new DummyRacingDao());
        RacingRequest racingRequest = new RacingRequest(
                "브리,토미,브라운",
                3
        );

        RacingResultDto racingResultDto = racingService.playRacingGame(racingRequest);

        assertThat(racingResultDto.getRacingCars()).hasSize(3);
        assertThat(racingResultDto.getWinners()).hasSizeBetween(1, 3);
    }
}
