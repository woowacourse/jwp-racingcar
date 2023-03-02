package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarsTest {
    Cars cars;
    Car car1;
    Car car2;
    Car car3;

    @BeforeEach
    void setUp() {
        car1 = new Car("car1");
        car2 = new Car("car2");
        car3 = new Car("car3");

        car1.move(4);
        car2.move(4);
        car2.move(4);
        car3.move(4);

        cars = new Cars();

        cars.addCar(car1);
        cars.addCar(car2);
        cars.addCar(car3);
    }

    @Test
    void pickWinners() {
    }

    @Nested
    @DisplayName("모든 차를 전진, 혹은 멈추는 동작에 대한 테스트")
    class MoveAll {
        @ParameterizedTest(name = "랜덤 숫자가 4로 전진하여 {0}번째 차의 위치는 {1}")
        @CsvSource(value = {"0:2", "1:3", "2:2"}, delimiter = ':')
        void case1(int index, int expectedPosition) {
            NumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);

            when(randomNumberGenerator.generate()).thenReturn(4);

            cars.moveAll(randomNumberGenerator);

            List<Car> moved = cars.getAll();
            assertThat(moved.get(index).getPosition()).isEqualTo(expectedPosition);
        }

        @ParameterizedTest(name = "랜덤 숫자가3으로 정지하여 {0}번째 차의 위치는 {1}")
        @CsvSource(value = {"0:1", "1:2", "2:1"}, delimiter = ':')
        void case2(int index, int expectedPosition) {
            NumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);

            when(randomNumberGenerator.generate()).thenReturn(3);

            cars.moveAll(randomNumberGenerator);

            List<Car> moved = cars.getAll();
            assertThat(moved.get(index).getPosition()).isEqualTo(expectedPosition);
        }
    }
}
