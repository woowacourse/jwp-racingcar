package racingcar.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameTest {

    @DisplayName("Name 객체 생성 시")
    @Nested
    class ValidationTest {
        @ParameterizedTest
        @ValueSource(strings = {"준팍", "무민", "빙봉", "검프"})
        @DisplayName("validation이 정상적으로 작동한다.")
        void givenNormalCarName_thenSuccess(String name) {
            assertThatCode(() -> new Name(name))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(strings = {"준팍무민빙봉검프", "aaaaaa", "bbbbbbbbb"})
        @DisplayName("5글자 초과의 이름이 들어왔을 경우 예외가 발생한다.")
        void givenFiveOverLength_thenFail(String name) {
            assertThatThrownBy(() -> new Name(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("1 ~ 5글자 사이의 이름을 입력해주세요.");
        }

        @ParameterizedTest
        @EmptySource
        @DisplayName("이름이 공백일 경우 예외가 발생한다.")
        void givenBlankName_thenFail(String name) {
            assertThatThrownBy(() -> new Name(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 공백일 수 없습니다.");
        }
    }


    @Test
    @DisplayName("문자열의 앞 뒤 공백은 제거한 후 저장한다.")
    void stripTest() {
        // given
        final Name name1 = new Name(" 준팍");
        final Name name2 = new Name("준팍 ");

        // when & then
        assertThat(name1.equals(name2)).isTrue();
        assertThat(name1.getValue()).isEqualTo(name2.getValue());
        assertThat(new HashSet<>(List.of(name1, name2))).hasSize(1);
    }
}
