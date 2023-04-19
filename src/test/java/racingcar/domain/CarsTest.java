package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {
    private final String carNames = "포르쉐,현대차,기아";


    @Test
    void 생성_실패_20개_이상의_이름이_존재() {
        String manyNames = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21";
        assertThatThrownBy(() -> Cars.from(manyNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경주 게임을 진행할 자동차는 최대 20개까지 생성할 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,b,a", "aaaa,aaaa", "1,1,2"})
    void 생성_실패_중복되는_이름이_존재(String names) {
        assertThatThrownBy(() -> Cars.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 중복될 수 없습니다.");
    }


    @Test
    void moveAll_메소드는_모든_자동차를_움직인다() {
        Cars cars = Cars.from(carNames);

        cars.moveAll(new AlwaysMoveGenerator());

        List<Integer> positions = cars.getUnmodifiableCars().stream()
                .map(Car::getPosition)
                .collect(Collectors.toList());

        Assertions.assertThat(positions).containsOnly(1);
    }

    @Test
    void getMaxPositionCars_메소드는_포지션이_가장_큰_자동차들을_반환() {
        Cars cars = Cars.from(carNames);
        cars.moveAll(new AlwaysMoveGenerator());
        List<Car> maxPositionCars = cars.getMaxPositionCars();
        Assertions.assertThat(maxPositionCars).isEqualTo(cars.getUnmodifiableCars());
    }

    private static class AlwaysMoveGenerator implements RandomNumberGenerator {

        @Override
        public int generate() {
            return 4;
        }
    }

    private static class NeverMoveGenerator implements RandomNumberGenerator {

        @Override
        public int generate() {
            return 3;
        }
    }
}
