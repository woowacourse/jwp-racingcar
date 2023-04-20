package racingcar.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingGameRequestTest {

    @DisplayName("입력값 dto를 생성할 때, 빈 값이 포함되어 있으면 예외가 발생한다.")
    @Test
    void exceptionWhenEmptyValueInRequest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> RacingGameRequest.of("", 1))
                .isInstanceOf(IllegalArgumentException.class);

    }

}