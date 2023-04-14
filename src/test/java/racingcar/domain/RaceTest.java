package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RaceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 10000})
    @DisplayName("정상적인 시도 횟수가 들어오면 예외가 발생하지 않는다.")
    void givenNormalRaceCount_thenSuccess(final int raceCount) {
        assertThatCode(() -> new Race(raceCount))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    @DisplayName("시도 횟수가 0 이하일 경우 예외가 발생한다.")
    void givenZeroRaceCount_thenFail(final int raceCount) {
        assertThatThrownBy(() -> new Race(raceCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(String.format("%d 이상의 값을 입력해 주세요.", 0));
    }
}
