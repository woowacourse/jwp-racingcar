package racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.provider.ValueSource;

class RacingGameTest {

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0})
    @DisplayName("round가 양의 정수가 아니라면 예외를 반환한다..")
    void validateRound(int wrongValue) {
        assertThatThrownBy(() -> new RacingGame(null, wrongValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 시도 횟수는 1이상이어야 합니다.");
    }

    @Test
    @DisplayName("최종 라운드까지 게임을 진행한다.")
    void findWinnerCars() {
        Cars cars = new Cars(List.of(new Car("A"), new Car("B"), new Car("C")));
        final TestNumberGenerator numberGenerator = new TestNumberGenerator(List.of(3, 4, 5));
        final RacingGame racingGame = new RacingGame(cars, 1);

        //when
        racingGame.play(numberGenerator);
        List<Car> actual = racingGame.findWinnerCars();

        //then
        assertThat(actual.size()).isEqualTo(2);
    }
}
