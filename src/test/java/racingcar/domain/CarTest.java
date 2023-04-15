package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef"})
    @DisplayName("자동차의 이름의 길이가 1이상 5이하가 아니면 오류가 발생한다.")
    void carGenerateTest(String name) {
        //when && then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 1자 이상 5자 이하여야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"3:0", "4:1"}, delimiter = ':')
    @DisplayName("자동차의 전진 여부를 판단한다.")
    void goForwardTest(int givenNumber, int expected) {
        //given
        Car car = new Car("pobi");

        //when
        car.move(givenNumber);
        final int actual = car.getPosition();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
