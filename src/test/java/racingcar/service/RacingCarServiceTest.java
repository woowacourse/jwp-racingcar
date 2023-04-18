package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingResultResponse;
import racingcar.repository.RacingCarRepository;
import racingcar.utils.NumberGenerator;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    @Mock
    RacingCarRepository racingCarRepository;

    @Mock
    NumberGenerator numberGenerator;

    @InjectMocks
    RacingCarService racingCarService;

    @Test
    @DisplayName("레이싱 게임이 진행되면 gameId를 반환해야 한다.")
    void playRacingGame_success() {
        // given
        List<String> carNames = List.of("glen", "raon");
        int tryCount = 10;

        doReturn(1)
                .when(numberGenerator)
                .generateNumber();

        doReturn(1)
                .when(racingCarRepository)
                .saveGame(10);

        // expect
        int gameId = racingCarService.playRacingGame(carNames, tryCount);
        assertThat(gameId)
                .isEqualTo(1);
    }

    @Test
    @DisplayName("레이싱 게임의 플레이 이력을 조회하면 RacingResultResponse의 List를 반환한다.")
    void searchGameHistory_success() {
        // given
        RacingCarDto racingCarDto1 = new RacingCarDto("glen", 1);
        RacingCarDto racingCarDto2 = new RacingCarDto("raon", 1);

        doReturn(Map.of(1, List.of(racingCarDto1, racingCarDto2)))
                .when(racingCarRepository)
                .findRacingCars();

        doReturn(Map.of(1, List.of("glen", "raon")))
                .when(racingCarRepository)
                .findWinners();

        // expect
        List<RacingResultResponse> racingResultResponses = racingCarService.searchGameHistory();

        assertThat(racingResultResponses)
                .hasSize(1);
        assertThat(racingResultResponses.get(0).getRacingCars())
                .hasSize(2)
                .contains(racingCarDto1, racingCarDto2);
        assertThat(racingResultResponses.get(0).getWinners())
                .hasSize(2)
                .contains("glen", "raon");
    }
}
