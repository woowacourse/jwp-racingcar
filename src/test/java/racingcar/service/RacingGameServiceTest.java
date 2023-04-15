package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.GameResultDAO;
import racingcar.dao.PlayerResultDAO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {
    @Mock
    private GameResultDAO gameResultDAO;

    @Mock
    private PlayerResultDAO playerResultDAO;

    @InjectMocks
    private RacingGameService racingGameService;

    @DisplayName("중복된 이름이 존재하는 경우, 예외가 발생한다.")
    @Test
    void playGameWithDuplicateNames() {
        assertThatThrownBy(() -> racingGameService.play(List.of("쥬니", "쥬니"), 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름의 길이가 1글자 이하, 5글자 초과인 경우, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"쥬니쥬니쥬니", "1234556789", ""})
    void playGameWithInvalidTryCount(String name) {
        assertThatThrownBy(() -> racingGameService.play(List.of(name), 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("허용되지 않은 시도 횟수인 경우, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0})
    void playGameWithInvalidTryCount(int tryCount) {
        assertThatThrownBy(() -> racingGameService.play(List.of("쥬니", "도치"), tryCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
