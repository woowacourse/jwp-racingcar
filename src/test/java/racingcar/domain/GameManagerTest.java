package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarStatusResponse;
import racingcar.dto.GameResultResponse;

class GameManagerTest {
    GameManager gameManager;

    @BeforeEach
    void setup() {
        gameManager = new GameManager(Cars.from(List.of("망고", "현구막")), new GameRound(10), () -> 4);
    }

    @DisplayName("라운드 진행 후 라운드 상태 반환 테스트")
    @Test
    void playGameRoundTest() {
        List<CarStatusResponse> carStatusResponses = gameManager.playGameRound();
        assertThat(carStatusResponses).hasSize(2);
    }

    @DisplayName("모든 라운드 진행 후 끝났는지 확인하는 테스트")
    @Test
    void isEndTest() {
        for (int round = 0; round < 10; round++) {
            gameManager.playGameRound();
        }
        assertThat(gameManager.isEnd()).isTrue();
    }

    @DisplayName("같은 숫자를 계속 반환하여  모두가 우승자인 테스트")
    @Test
    void decideWinnerTest() {
        for (int round = 0; round < 10; round++) {
            gameManager.playGameRound();
        }
        GameResultResponse gameResultResponse = gameManager.decideWinner();
        assertThat(gameResultResponse.getWinnerNames()).containsExactly("망고", "현구막");
    }
}
