package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LapTest {
    @Test
    @DisplayName("reduce 메소드에 대한 테스트")
    void reduceTest() {
        int initialLap = 5;
        Lap lap = new Lap(initialLap);
        for (int i = 0; i < initialLap; i++) {
            lap.reduce();
        }

        assertThat(lap.isPlayable()).isFalse();
    }

    @ParameterizedTest()
    @CsvSource(value = {"1,true", "0,false"})
    @DisplayName("isFinish 메소드에 대한 테스트")
    void isFinishTest(int lapLeft, boolean expected) {
        Lap lap = new Lap(lapLeft);

        assertThat(lap.isPlayable())
                .isEqualTo(expected);
    }
}
