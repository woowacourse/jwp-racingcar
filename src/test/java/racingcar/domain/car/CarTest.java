package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {
    @Nested
    class InitializeTest {
        @ParameterizedTest(name = "{0} 일 때 IllegalArgumentException 발생")
        @DisplayName("유효하지 않은 길이의 이름")
        @ValueSource(strings = {"abcdef4", "adffdsd", "dadfewe", "", " "})
        void throwExceptionWhenInvalidNameLength(String name) {
            Assertions.assertThatThrownBy(() -> new Car(name))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("이름이 빈 문자열일 때 IllegalArgumentException 발생")
        void throwExceptionWhenNameIsEmpty() {
            Assertions.assertThatThrownBy(() -> new Car(""))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class MoveTest {
        @ParameterizedTest
        @DisplayName("1회 이동했을 때, 위치가 정상적으로 증가하는지 테스트")
        @CsvSource(value = {"1:0", "2:0", "3:0", "4:1", "5:1"}, delimiter = ':')
        void moveOneStep(int pickedNumber, int expectedPosition) {
            Car car = new Car("hong");
            car.moveDependingOn(pickedNumber);
            assertThat(car.getPosition()).isEqualTo(expectedPosition);
        }
    }

    @DisplayName("같은 위치에 있는 차를 확인할 수 있다.")
    @ParameterizedTest(name = "위치가 {0}, {1} 일 때 두 위치 동일 여부는 {2}이다.")
    @CsvSource(value = {"3:3:true", "3:1:false"}, delimiter = ':')
    void checkCarInSamePosition(int targetPositionValue, int carPositionValue, boolean isSame) {
        //given
        Position position = new Position(targetPositionValue);
        Car car = new Car("브리", carPositionValue);
        //when
        boolean isInSamePosition = car.isInSamePosition(position);
        //then
        assertThat(isInSamePosition).isEqualTo(isSame);
    }
}
