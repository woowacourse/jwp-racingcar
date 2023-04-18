package racingcar.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RacingGameTest {

    @ParameterizedTest
    @MethodSource("parameterProvider")
    void getWinnersTest(List<String> carNames, List<Integer> determinedNumbers, int gameTry,
                        List<String> expectedWinners) {
        DeterminedNumberGenerator determinedNumberGenerator = DeterminedNumberGenerator.createByList(determinedNumbers);
        List<Car> cars = carNames.stream()
                .map(Car::createBy)
                .collect(Collectors.toList());
        RacingGame racingGame = new RacingGame(cars, determinedNumberGenerator, new Coin(gameTry));

        racingGame.play();

        assertEquals(
                racingGame.getWinners().stream().map(Car::getCarName).collect(Collectors.toList()), expectedWinners
        );
    }

    private Stream<Arguments> parameterProvider() {
        return Stream.of(
                Arguments.of(List.of("pobi", "crong"), List.of(5, 3, 9, 0, 0, 9), 3, List.of("pobi")),
                Arguments.of(List.of("pobi", "crong"), List.of(3, 5, 9, 0, 0, 9), 3, List.of("crong")),
                Arguments.of(List.of("pobi", "crong"), List.of(4, 4, 4, 4, 4, 4), 3, List.of("pobi", "crong")),
                Arguments.of(List.of("pobi", "crong"), List.of(0, 0, 0, 0, 0, 0), 3, List.of("pobi", "crong")),
                Arguments.of(List.of("pobi", "crong", "hadi"), List.of(3, 4, 9, 6, 7, 6, 0, 1, 2), 3,
                        List.of("crong", "hadi")),
                Arguments.of(List.of("pobi", "crong", "hadi"), List.of(7, 4, 9, 6, 7, 6, 0, 1, 2), 3,
                        List.of("pobi", "crong", "hadi"))
        );
    }
}
