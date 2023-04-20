package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.GameResultResponse;

class GameManagerTest {
    GameManager gameManager;

    @BeforeEach
    void setup() {
        gameManager = new GameManager(Cars.from(List.of("망고", "현구막")), new GameRound(10), () -> 4);
    }

    @DisplayName("같은 숫자를 계속 반환하여  모두가 우승자인 테스트")
    @Test
    void decideWinnerTest() {
        gameManager.play();
        GameResultResponse gameResultResponse = new GameResultResponse(gameManager.decideWinner());
        assertThat(gameResultResponse.getWinnerNames()).containsExactly("망고", "현구막");
    }
}
