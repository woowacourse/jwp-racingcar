package racingcar.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.dto.RacingCarDto;

import java.util.stream.Stream;

class RacingGameTest {
    @ParameterizedTest
    @MethodSource("전진_결과_데이터")
    @DisplayName("range범위에 따른 전진 결과 테스트")
    public void 전진_결과_테스트(int num, int expectedPosition) {
        NumberGenerator numberGenerator = new DefaultNumberGenerator(num);

        RacingGame racingGame = new RacingGame(List.of(new RacingCar("오잉")), numberGenerator);
        racingGame.runRound();
        RacingCarDto racingCarDto = RacingCarDto.from(racingGame.getStatus().get(0));
        Integer position = racingCarDto.getPosition();

        Assertions.assertEquals(expectedPosition, position);
    }

    static Stream<Arguments> 전진_결과_데이터() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(9, 2)
        );
    }
}
