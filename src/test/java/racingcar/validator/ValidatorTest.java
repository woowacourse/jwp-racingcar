package racingcar.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("예외 경우 파싱 테스트")
class ValidatorTest {

    @ParameterizedTest()
    @ValueSource(strings = {"1", "3", "9", "100", "300", "500"})
    public void 횟수_유효성_검사(String tryCount) {
        assertDoesNotThrow(() -> Validator.validateTryCount(tryCount));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"a", "0", "-100", "1abc", "테스트"})
    public void 횟수_예외_검사(String tryCount) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                Validator.validateTryCount(tryCount)
        );
        assertEquals("시도할 횟수는 자연수만 가능합니다.", exception.getMessage());
    }

    @ParameterizedTest()
    @MethodSource("이름_유효성_검사_데이터_생성")
    public void 이름_유효성_검사(List<String> names) {
        assertDoesNotThrow(() -> Validator.validateNames(names));
    }

    static Stream<Arguments> 이름_유효성_검사_데이터_생성() {
        return Stream.of(
                Arguments.of(Arrays.asList("홍고", "오잉")),
                Arguments.of(Arrays.asList("1", "22", "333")),
                Arguments.of(Arrays.asList("hongo", "oing"))
        );
    }

    @ParameterizedTest()
    @MethodSource("길이_예외_검사_데이터_생성")
    public void 길이_예외_검사(List<String> names) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                Validator.validateNames(names)
        );
        assertEquals("자동차 이름은 5자이하만 가능합니다.", exception.getMessage());
    }

    static Stream<Arguments> 길이_예외_검사_데이터_생성() {
        return Stream.of(
                Arguments.of(Arrays.asList("홍고홍고홍고", "오잉")),
                Arguments.of(Arrays.asList("123123", "456", "789")),
                Arguments.of(Arrays.asList("hongo", ""))
        );
    }

    @ParameterizedTest()
    @MethodSource("중복_예외_검사_데이터_생성")
    public void 중복_예외_검사(List<String> names) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                Validator.validateNames(names)
        );
        assertEquals("자동차 이름은 중복될 수 없습니다.", exception.getMessage());
    }

    static Stream<Arguments> 중복_예외_검사_데이터_생성() {
        return Stream.of(
                Arguments.of(Arrays.asList("홍고", "오잉", "홍고")),
                Arguments.of(Arrays.asList("123", "456", "123")),
                Arguments.of(Arrays.asList("hongo", "hongo", "oing"))
        );
    }
}
