package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingResultResponse;
import racingcar.repository.RacingCarJdbcRepository;
import racingcar.utils.NumberGenerator;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    @Mock
    RacingCarJdbcRepository racingCarJdbcRepository;

    @Mock
    NumberGenerator numberGenerator;

    @InjectMocks
    RacingCarService racingCarService;

    @Test
    @DisplayName("레이싱 게임이 진행되면 게임 결과를 반환해야 한다.")
    void playRacingGame_success() {
        // given
        List<String> carNames = List.of("glen", "raon");
        int tryCount = 10;

        RacingCarDto racingCarDto2 = new RacingCarDto("raon", 6);
        RacingCarDto racingCarDto1 = new RacingCarDto("glen", 4);

        doReturn(1)
                .when(numberGenerator)
                .generateNumber();

        doReturn(1)
                .when(racingCarJdbcRepository)
                .saveGame(10);

        doReturn(List.of("raon"))
                .when(racingCarJdbcRepository)
                .findWinnersByGameId(anyInt());

        doReturn(List.of(racingCarDto1, racingCarDto2))
                .when(racingCarJdbcRepository)
                .findRacingCarsByGameId(anyInt());

        // expect
        RacingResultResponse result =
                racingCarService.playRacingGame(carNames, tryCount);
        assertThat(result.getWinners())
                .containsExactly("raon");
        assertThat(result.getRacingCars())
                .hasSize(2)
                .contains(racingCarDto1, racingCarDto2);
    }

    @Test
    @DisplayName("레이싱 게임의 플레이 이력을 조회하면 RacingResultResponse의 List를 반환한다.")
    void searchGameHistory_success() {
        // given
        RacingCarDto racingCarDto1 = new RacingCarDto("glen", 1);
        RacingCarDto racingCarDto2 = new RacingCarDto("raon", 1);

        doReturn(Map.of(1, List.of(racingCarDto1, racingCarDto2)))
                .when(racingCarJdbcRepository)
                .findRacingCars();

        doReturn(Map.of(1, List.of("glen", "raon")))
                .when(racingCarJdbcRepository)
                .findWinners();

        // expect
        List<RacingResultResponse> results = racingCarService.searchGameHistory();

        assertThat(results)
                .hasSize(1);
        assertThat(results.get(0).getRacingCars())
                .hasSize(2)
                .contains(racingCarDto1, racingCarDto2);
        assertThat(results.get(0).getWinners())
                .hasSize(2)
                .contains("glen", "raon");
    }
}
