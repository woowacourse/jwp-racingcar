package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

        // when
        doReturn(1)
                .when(numberGenerator)
                .generateNumber();

        doReturn(1)
                .when(racingCarRepository)
                .saveGame(10);

        // then
        int gameId = racingCarService.playRacingGame(carNames, tryCount);
        assertThat(gameId)
                .isEqualTo(1);
    }
}
