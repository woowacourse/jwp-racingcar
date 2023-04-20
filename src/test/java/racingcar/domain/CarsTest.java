package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsTest {

    Cars cars;

    @Test
    @DisplayName("차 추가 테스트")
    void givenCar_whenSavingCar_thenSavesCar() {
        cars = new Cars("test", RandomNumberGenerator.makeInstance());

        assertThat(cars).extracting("cars").asList().hasSize(1);
    }

    @Test
    @DisplayName("move 실행시 value가 1 증가한다.")
    void givenCarsHavingACar_whenMovesCar_thenAddsPosition() {
        cars = new Cars("test",
            new TestRandomNumberGenerator(
                List.of(4)
            ));

        cars.move();

        assertThat(cars).extracting("cars").asList().element(0).extracting("position").isEqualTo(1);
    }

    @Test
    @DisplayName("position 값이 제일 높은 Car의 이름을 반환한다.")
    void givenMovedCar_whenFindsWinnerNames_thenReturnsWinnerName() {
        cars = new Cars("test,fox",
            new TestRandomNumberGenerator(
                List.of(3, 4)
            ));
        cars.move();

        List<String> winners = cars.getWinnerNames();

        assertThat(winners).containsExactly("fox");
    }

    @Test
    @DisplayName("position 값이 제일 높은 Car가 여러 대라면 모든 우승자 이름을 반환한다.")
    void givenCarsHavingMultipleWinners_whenFindsWinnerNames_thenReturnsAllWinnerNames() {
        cars = new Cars("test,fox",
            new TestRandomNumberGenerator(
                List.of(4, 4)
            ));
        cars.move();

        List<String> winnerNames = cars.getWinnerNames();

        assertThat(winnerNames).containsExactly("test", "fox");
    }

    static class TestRandomNumberGenerator implements NumberGenerator {

        private final Iterator<Integer> testNumber;

        public TestRandomNumberGenerator(List<Integer> testNumber) {
            this.testNumber = testNumber.iterator();
        }

        @Override
        public int generateNumber() {
            if (testNumber.hasNext()) {
                return testNumber.next();
            }

            throw new IllegalArgumentException();
        }
    }
}