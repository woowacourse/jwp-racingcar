package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.RacingGame;


class RacingServiceTest {

    @Test
    @DisplayName("플레이어가 한명일 때 GameResultDto를 반환한다.")
    void shouldReturnCorrectlyWhenInputOnePlayer() {
        RacingService racingService = new RacingService(new DummyRacingDao());

        RacingGame racingGame = racingService.playRacingGame("브리", 3);

        assertThat(racingGame.getCars()).hasSize(1);
        assertThat(racingGame.getWinners()).hasSize(1);
        assertThat(racingGame.getWinners().get(0).getCarName()).isEqualTo("브리");
    }

    @Test
    @DisplayName("플레이어가 여려명일 때 GameResultDto를 반환한다.")
    void shouldReturnCorrectlyWhenInputManyPlayer() {
        RacingService racingService = new RacingService(new DummyRacingDao());

        RacingGame racingGame = racingService.playRacingGame("브리,토미,브라운", 3);

        assertThat(racingGame.getCars()).hasSize(3);
        assertThat(racingGame.getWinners()).hasSizeBetween(1, 3);
    }
}
