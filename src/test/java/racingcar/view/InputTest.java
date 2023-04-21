package racingcar.view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InputTest {
    private static Input input;

    @BeforeAll
    static void setup() {
        input = new Input();
    }

    @Test
    void trycount_success() {
        String n = "123";
        assertThat(input.getTryCount(n)).isEqualTo(123);
    }

    @Test
    @DisplayName("시도 횟수가 음수로 들어왔을 때 에러를 확인하는 테스트")
    void trycount_negative() {
        String n = "-123";
        assertThatThrownBy(() -> input.getTryCount(n))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]", "숫자");
    }

    @Test
    @DisplayName("시도 횟수가 0일 때 에러를 확인하는 테스트")
    void trycount_zero() {
        String n = "0";
        assertThatThrownBy(() -> input.getTryCount(n))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]", "1이상");
    }

    @Test
    @DisplayName("시도 횟수가 숫자가 아닐 때 에러를 확인하는 테스트")
    void trycount_nondigit() {
        String n = "abc";
        assertThatThrownBy(() -> input.getTryCount(n))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]", "숫자");
    }
}
