package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

        given(numberGenerator.generateNumber())
                .willReturn(1);

        given(racingCarRepository.saveGame(anyInt()))
                .willReturn(1);

        // when
        int gameId = racingCarService.playRacingGame(carNames, tryCount);

        // then
        assertThat(gameId)
                .isEqualTo(1);
    }
}
