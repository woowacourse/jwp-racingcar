package racingcar.domain;


import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RaceTest {

    List<String> correctCarNames = List.of("a", "b", "c");
    NumberGenerator numberGenerator = new RandomNumberGenerator();

    @Test
    void 시도횟수가_1회보다_적을때_오류를던진다() {
        // given
        int count = 0;
        // when then
        assertThatThrownBy(() -> {
            new Race(count, correctCarNames, numberGenerator);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 시도횟수입니다.(1 ~ 10)");
    }

    @Test
    void 시도횟수가_10회_초과일시_오류를던진다() {
        // given
        int count = 11;
        // when then
        assertThatThrownBy(() -> {
            new Race(count, correctCarNames, numberGenerator);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 시도횟수입니다.(1 ~ 10)");
    }

    @ValueSource(ints = {1, 10})
    @ParameterizedTest
    void 올바른_시도횟수인경우(int count) {
        assertThatNoException().isThrownBy(() -> {
                    new Race(count, correctCarNames, numberGenerator);
                }
        );
    }
}
