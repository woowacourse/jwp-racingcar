package racingcar.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParserTest {
    public static final String DELIMITER = ",";

    @ParameterizedTest
    @MethodSource("기본_파싱_테스트_데이터_생성")
    public void 기본_파싱_테스트(String text, String delimiter, List<String> expected) {
        Assertions.assertThat(Parser.parsing(text, delimiter)).isEqualTo(expected);
    }

    static Stream<Arguments> 기본_파싱_테스트_데이터_생성() {
        return Stream.of(
                Arguments.of("a,b,c", DELIMITER, Arrays.asList("a", "b", "c")),
                Arguments.of("abc,def", DELIMITER, List.of("abc", "def"))
        );
    }

    @ParameterizedTest
    @MethodSource("예외_파싱_테스트_데이터_생성")
    public void 예외_파싱_테스트(String text, String delimiter) {
        Assertions.assertThatThrownBy(() ->
                Parser.parsing(text, delimiter)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> 예외_파싱_테스트_데이터_생성() {
        return Stream.of(
                Arguments.of("", DELIMITER),
                Arguments.of(null, DELIMITER)
        );
    }
}
