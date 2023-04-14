package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.dto.RacingCarDto;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingGameTest {
    @ParameterizedTest
    @MethodSource("전진_결과_데이터")
    public void 전진_결과_테스트(int num, int expectedPosition) {
        NumberGenerator numberGenerator = new DefaultNumberGenerator(num);

        RacingGame racingGame = new RacingGame(List.of(new RacingCar("오잉")), numberGenerator);
        racingGame.runRound();
        RacingCarDto racingCarDto = RacingCarDto.from(racingGame.getStatus().get(0));
        Integer position = racingCarDto.getPosition();

        assertEquals(expectedPosition, position);
    }

    static Stream<Arguments> 전진_결과_데이터() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(9, 2)
        );
    }

    @Test
    public void 결과_계산_테스트() {
        RacingCar oing = new RacingCar("오잉");
        oing.advance(9);
        oing.advance(9);

        RacingCar poi = new RacingCar("포이");
        poi.advance(9);
        poi.advance(1);

        RacingGame racingGame = new RacingGame(List.of(oing, poi), new RandomNumberGenerator());

        Map<RacingCar, GameResult> results = racingGame.calculateResult();
        GameResult oingResult = results.get(oing);
        GameResult poiResult = results.get(poi);

        assertThat(oingResult).isEqualTo(GameResult.WIN);
        assertThat(poiResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    public void 승자_찾기_테스트() {
        RacingCar oing = new RacingCar("오잉");
        oing.advance(9);
        oing.advance(9);

        RacingCar bree = new RacingCar("브리");
        bree.advance(9);
        bree.advance(9);

        RacingCar poi = new RacingCar("포이");
        poi.advance(9);
        poi.advance(1);

        RacingGame racingGame = new RacingGame(List.of(oing, poi, bree), new RandomNumberGenerator());
        List<String> winners = racingGame.findWinningCarsName();

        assertThat(winners).containsExactly("오잉", "브리");
    }
}
