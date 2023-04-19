package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameManagerTest {
    GameManager gameManager;

    @BeforeEach
    void setup() {
        Cars cars = Cars.from(List.of("가가", "나나", "다다"));
        GameRound gameRound = new GameRound(5);
        gameManager = new GameManager(() -> 4, cars, gameRound);
    }

    @DisplayName("같은 숫자를 계속 반환하여  모두가 우승자인 테스트")
    @Test
    void decideWinnerTest() {
        gameManager.run();
        List<Car> winnerCars = gameManager.getWinnerCars();
        List<String> winnerNames = winnerCars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        assertThat(winnerNames).containsExactly("가가", "나나", "다다");
    }

    static Stream<Arguments> carNamesDummy() {
        return Stream.of(
                Arguments.of(List.of("가가", "나나", "다다")),
                Arguments.of(List.of("11", "22", "33")),
                Arguments.of(List.of("aa", "bb", "cc"))
        );
    }
}
