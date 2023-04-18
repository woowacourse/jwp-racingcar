package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.cars.RacingCar;

public class RacingCarTest {
    @Nested
    class InitializeTest {
        @ParameterizedTest(name = "{0} 일 때 IllegalArgumentException 발생")
        @DisplayName("유효하지 않은 길이의 이름")
        @ValueSource(strings = {"abcdef4", "adffdsd", "dadfewe", "", " "})
        void throwExceptionWhenInvalidNameLength(String name) {
            Assertions.assertThatThrownBy(() -> new RacingCar(0, name))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("이름이 빈 문자열일 때 IllegalArgumentException 발생")
        void throwExceptionWhenNameIsEmpty() {
            Assertions.assertThatThrownBy(() -> new RacingCar(0, ""))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class MoveTest {
        @ParameterizedTest
        @DisplayName("1회 이동했을 때, 위치가 정상적으로 증가하는지 테스트")
        @CsvSource(value = {"1:0", "2:0", "3:0", "4:1", "5:1"}, delimiter = ':')
        void moveOneStep(int pickedNumber, int expectedPosition) {
            RacingCar racingCar = new RacingCar(0, "hong");
            racingCar.moveDependingOn(pickedNumber);
            Assertions.assertThat(racingCar).extracting("position")
                    .isEqualTo(expectedPosition);
        }
    }

    @ParameterizedTest(name = "게임아이디({0})와 이름이 같으면 같은 자동차이다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 100})
    void equals(int gameId) {
        String name = "서브웨이";
        RacingCar racingCar = new RacingCar(gameId, name);

        Assertions.assertThat(racingCar).isEqualTo(new RacingCar(gameId, name));
    }
}