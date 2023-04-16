package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
import racingcar.repository.MySqlRacingCarRepository;
import racingcar.utils.NumberGenerator;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    @Mock
    MySqlRacingCarRepository mySqlRacingCarRepository;

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

        given(mySqlRacingCarRepository.saveGame(anyInt()))
                .willReturn(1);

        // when
        int gameId = racingCarService.playRacingGame(carNames, tryCount);

        // then
        assertThat(gameId)
                .isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("시도 횟수가 0 또는 음수거나 25를 초과하면 예외가 발생해야 한다.")
    @ValueSource(ints = {-1, 0, 26})
    void playRacingGame_invalidTryCount(int tryCount) {
        // given
        List<String> carNames = List.of("glen", "raon");

        // expect
        assertThatThrownBy(() -> racingCarService.playRacingGame(carNames, tryCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 이름이 주어지면 예외가 발생해야 한다.")
    void playRacingGame_duplicateName() {
        // given
        List<String> carNames = List.of("glen", "glen");

        // expect
        assertThatThrownBy(() -> racingCarService.playRacingGame(carNames, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 중복된 차 이름이 있습니다.");
    }

    @Test
    @DisplayName("이름이 5글자를 초과하면 예외가 발생해야 한다.")
    void playRacingGame_nameLength() {
        // given
        List<String> carNames = List.of("glen", "glenfiddich");

        // expect
        assertThatThrownBy(() -> racingCarService.playRacingGame(carNames, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 자동차 이름은 5자 이하여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"글렌.", "ㄱㄹ", "글:렌", "글렌,"})
    @DisplayName("이름이 영문이나 한글이 아니면 예외가 발생해야 한다.")
    void playRacingGame_invalidName(String input) {
        // given
        List<String> carNames = List.of(input);

        // expect
        assertThatThrownBy(() -> racingCarService.playRacingGame(carNames, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 자동차 이름은 한글 혹은 영문만 가능합니다.");
    }
}
