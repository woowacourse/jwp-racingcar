package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Car 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CarTest {

    @ParameterizedTest(name = "move 메서드는 값을 입력받아 4이상인 경우 전진한다. 초기 위치: 0 입력값: {0} 동작 후 위치: {1}")
    @CsvSource(value = {"4,1", "3,0"})
    void move_메서드는_값을_입력받아_4이상인_경우_전진한다(final int value, final int position) {
        // given
        Car car = new Car("Herb");

        // when
        car.move(value);

        // then
        assertThat(car.getPosition()).isEqualTo(position);
    }

    @Test
    void isSamePosition_메서드는_위치가_다르면_false를_반환한다() {
        // given
        Car car = new Car("Herb");
        car.move(Integer.MAX_VALUE);

        // when
        boolean result = car.isSamePosition(new Car("Herb2"));

        // then
        assertThat(result).isFalse();
    }

    @Test
    void isSamePosition_메서드는_위치가_같으면_true를_반환한다() {
        // given
        Car car = new Car("Herb");

        // when
        boolean result = car.isSamePosition(new Car("Herb2"));

        // then
        assertThat(result).isTrue();
    }
}
