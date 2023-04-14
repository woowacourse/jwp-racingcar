package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @DisplayName("정상 이름 입력")
    @ParameterizedTest(name = "name = {0}")
    @ValueSource(strings = {"아벨", "split"})
    void name(String name) {
        assertThatNoException()
                .isThrownBy(() -> new Name(name, 0));
    }
    
    @DisplayName("이름 길이 범위 초과 시 예외 발생")
    @ParameterizedTest(name = "name = {0}")
    @ValueSource(strings = {"", "splited"})
    void out_of_range_exception(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name, 0))
                .withMessage("부적절한 이름입니다.");
    }
    
    @DisplayName("들어갈 수 없는 문자 존재할 시 예외 발생")
    @ParameterizedTest(name = "name = {0}")
    @ValueSource(strings = {"spli-", "spl-t", "-splt"})
    void not_correct_char(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name, 0))
                .withMessage("부적절한 이름입니다.");
    }
}