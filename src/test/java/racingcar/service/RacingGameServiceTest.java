package racingcar.service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import racingcar.dto.GameResultResponse;
import racingcar.dto.RacingGameRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameServiceTest {

    @LocalServerPort
    private int port;
    @Autowired
    private RacingGameService racingGameService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("플레이어가 한명일 때 GameResultDto가 잘 반환된다")
    void shouldReturnCorrectlyWhenInputOnePlayer() {
        RacingGameRequest racingGameRequest = new RacingGameRequest(
                "브리",
                3
        );

        GameResultResponse gameResultResponse = racingGameService.playRacingGame(racingGameRequest);

        assertThat(gameResultResponse.getRacingCars()).hasSize(1);
        assertThat(gameResultResponse.getWinners()).isEqualTo("브리");
    }

    @Test
    @DisplayName("플레이어가 여려명일 때 GameResultDto가 잘 반환된다")
    void shouldReturnCorrectlyWhenInputManyPlayer() {
        RacingGameRequest racingGameRequest = new RacingGameRequest(
                "브리,토미,브라운",
                3
        );

        GameResultResponse gameResultResponse = racingGameService.playRacingGame(racingGameRequest);

        assertThat(gameResultResponse.getRacingCars()).hasSize(3);
        assertThat(gameResultResponse.getWinners()).isNotNull();
    }
}
