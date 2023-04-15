package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class CarsTest {

    @Test
    @DisplayName("단독 우승 테스트")
    void getWinner() {
        Cars cars = new Cars(List.of("move", "stop"));

        List<Car> carObjects = cars.getCars();
        Car moveCar = carObjects.get(0);
        moveCar.move();

        assertThat(cars.winners().get(0).getName()).isEqualTo("move");
    }

    @Test
    @DisplayName("공동 우승 테스트")
    void getWinners() {
        Cars cars = new Cars(Arrays.asList("car1", "car2"));

        List<String> winnerNames = cars.winners()
                .stream()
                .map(Car::getName)
                .collect(Collectors.toList());

        assertThat(winnerNames).isEqualTo(List.of("car1", "car2"));
    }

    @Test
    @DisplayName("두 개 미만의 자동차가 생성되면 예외를 발생한다")
    void 두_개_미만의_자동차가_생성되면_예외를_발생한다() {
        assertThatIllegalArgumentException().isThrownBy(
                () ->  new Cars(List.of("car1"))
        );
    }

    @Test
    @DisplayName("두 개 이상의 자동차가 생성되면 객체를 정상 생성한다")
    void 두_개_이상의_자동차가_생성되면_객체를_정상_생성한다() {
        assertThatNoException().isThrownBy(
                () ->  new Cars(List.of("car1", "car2"))
        );
    }

    @Test
    @DisplayName("자동차 이름이 중복되면 예외를 발생한다")
    void 자동차_이름이_중복되면_예외를_발생한다() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Cars(List.of("car1", "car1"))
        );
    }

    @Test
    @DisplayName("자동차 이름이 중복되지 않으면 객체를 정상 생성한다")
    void 자동차_이름이_중복되지_않으면_객체를_정상_생성한다() {
        assertThatNoException().isThrownBy(
                () ->  new Cars(List.of("car1", "car2"))
        );
    }

    @Test
    @DisplayName("우승자의 이름을 반환한다")
    void 우승자의_이름을_반환한다() {
        Cars cars = new Cars(List.of("파워", "도기"));

        List<String> winnerNames = cars.winnerNames();

        assertThat(winnerNames).isEqualTo(List.of("파워", "도기"));
    }
}
