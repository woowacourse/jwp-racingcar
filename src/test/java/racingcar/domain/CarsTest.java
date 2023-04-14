package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CarsTest {

    @Test
    @DisplayName("단독 우승 테스트")
    void getWinner() {
        Car success = new TestCarSuccess("Success");
        Car fail = new TestCarFail("Fail");
        Cars cars = new Cars(Arrays.asList(success, fail));
        cars.moveAll();
        assertThat(cars.getWinner()).isEqualTo(List.of(success));
    }

    @Test
    @DisplayName("공동 우승 테스트")
    void getWinners() {
        Car success1 = new TestCarSuccess("Success1");
        Car success2 = new TestCarSuccess("Success2");
        Cars cars = new Cars(Arrays.asList(success1, success2));
        cars.moveAll();
        assertThat(cars.getWinner()).isEqualTo(List.of(success1, success2));
    }

    private static class TestCarSuccess extends Car {

        public TestCarSuccess(String name) {
            super(name);
        }

        @Override
        public boolean isMove() {
            return true;
        }
    }

    private static class TestCarFail extends Car {

        public TestCarFail(String name) {
            super(name);
        }

        @Override
        public boolean isMove() {
            return false;
        }
    }
}
