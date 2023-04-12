package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static racingcar.provider.TestProvider.createTestRace;

public class RaceTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 10000})
    @DisplayName("정상적인 시도 횟수가 들어오면 예외가 발생하지 않는다.")
    void givenNormalRaceCount_thenSuccess(final int raceCount) {
        assertThatCode(() -> Race.create(raceCount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    @DisplayName("시도 횟수가 0 이하일 경우 예외가 발생한다.")
    void givenZeroRaceCount_thenFail(final int raceCount) {
        assertThatThrownBy(() -> Race.create(raceCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("%d 이상의 값을 입력해 주세요.", 0));
    }

    @Test
    @DisplayName("사용자가 입력한 횟수만큼 경주를 진행했다면 false를 리턴한다.")
    void givenRaceOrder_thenReturnTrue() {
        // given
        int userRaceCount = 3;
        Race testRace = createTestRace(userRaceCount);

        // when
        boolean isRaceFinish = testRace.isRunning(userRaceCount);

        // then
        assertThat(isRaceFinish)
                .isFalse();
    }

    @Test
    @DisplayName("사용자가 입력한 횟수만큼 경주를 진행하지 않았다면 true를 리턴한다.")
    void givenRaceOrder_thenReturnFalse() {
        // given
        final int userRaceCount = 3;
        final Race testRace = createTestRace(userRaceCount);
        final int raceCount = 2;

        // when
        boolean isRaceFinish = testRace.isRunning(raceCount);

        // then
        assertThat(isRaceFinish)
                .isTrue();
    }
}
