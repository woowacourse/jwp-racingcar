package racingcar.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrialTest {

    @DisplayName("trial이 범위에 맞지 않으면 예외가 발생한다")
    @Test
    void dd(){
        assertThatThrownBy(()-> new Trial(0))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("시도 횟수는", "미만일 수 없어요.");
    }
}
