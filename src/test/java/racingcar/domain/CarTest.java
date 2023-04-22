package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CarTest {
    @DisplayName("Car 이름의 길이가 1보다 작거나 5보다 큰 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"123456", ""})
    void throwExceptionWhenNameLengthIsNotBetween1And5(final String nameValue) {
        assertThatThrownBy(() -> new Car(nameValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Car 이름의 길이가 1이상 5이하일 경우 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "12345"})
    void create(final String nameValue) {
        assertDoesNotThrow(() -> new Car(nameValue));
    }

    @DisplayName("4 ~ 9 사이의 숫자를 받을 경우 Car는 움직인다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void move(final int number) {
        //given
        final Car car = new Car("헤나");

        //when
        car.move(() -> number);
        final Position movedPosition = car.getPosition();

        //then
        assertThat(movedPosition).isEqualTo(new Position(1));
    }

    @DisplayName("자동차의 move메소드는 전전하지 않는 경우 위치가 유지된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 10, 11, 12})
    void notMove(final int number) {
        //given
        final Car car = new Car("헤나");

        //when
        car.move(() -> number);
        final Position notMovedPosition = car.getPosition();

        //then
        assertThat(notMovedPosition).isEqualTo(Position.ZERO);
    }
}
