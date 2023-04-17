package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.GameResultDto;
import racingcar.dto.RacingGameRequest;


class RacingGameServiceTest {

    @Test
    @DisplayName("플레이어가 한명일 때 GameResultDto가 잘 반환된다")
    void shouldReturnCorrectlyWhenInputOnePlayer() {
        RacingGameService racingGameService = new RacingGameService(new DummyRacingGameDao());
        RacingGameRequest racingGameRequest = new RacingGameRequest(
                "브리",
                3
        );

        GameResultDto gameResultDto = racingGameService.playRacingGame(racingGameRequest);

        assertThat(gameResultDto.getRacingCars()).hasSize(1);
        assertThat(gameResultDto.getWinners()).isEqualTo("브리");
    }

    @Test
    @DisplayName("플레이어가 여려명일 때 GameResultDto가 잘 반환된다")
    void shouldReturnCorrectlyWhenInputManyPlayer() {
        RacingGameService racingGameService = new RacingGameService(new DummyRacingGameDao());
        RacingGameRequest racingGameRequest = new RacingGameRequest(
                "브리,토미,브라운",
                3
        );

        GameResultDto gameResultDto = racingGameService.playRacingGame(racingGameRequest);

        assertThat(gameResultDto.getRacingCars()).hasSize(3);
        assertThat(gameResultDto.getWinners()).isNotNull();
    }
}
