package racingcar.model.car;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import racingcar.model.manager.ThresholdCarMoveManager;
import racingcar.util.CarNameValidator;

class CarsTest {

    @Nested
    @DisplayName("비정상 입력값이 들어온 케이스")
    class invalidInputTest {

        @Test
        @DisplayName("경주에 참여하는 자동차가 1대 이하면 예외처리 한다.")
        void carNumberTest() {
            Car gitJjang = new Car(new Name("깃짱"));
            List<Car> cars = new ArrayList<>(List.of(gitJjang));

            assertThatThrownBy(() -> new Cars(cars))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(CarNameValidator.Message.EXCEPTION_CAR_NUMBER.getMessage());
        }
    }

    @Nested
    @DisplayName("정상 입력값이 들어온 케이스")
    class validInputTest {

        @Test
        @DisplayName("자동차 이름들이 올바른 경우 잘 추가되는지 검사한다.")
        void 정상_입력() {
            Car gitJjang = new Car(new Name("깃짱"));
            Car irene = new Car(new Name("이리내"));
            List<Car> cars = new ArrayList<>(List.of(gitJjang, irene));

            assertThatCode(() -> new Cars(cars)).doesNotThrowAnyException();
        }

    }

    @Test
    @DisplayName("더 많이 이동한 차량이 우승자로 판단되는지 검사한다.")
    void winnerTest() {
        Car gitJjang = new Car(new Name("깃짱"));
        Car irene = new Car(new Name("이리내"));
        Cars cars = new Cars(Arrays.asList(gitJjang, irene));

        gitJjang.move(true);
        irene.move(true);
        gitJjang.move(true);
        irene.move(false);

        assertThat(cars.getWinners()).contains("깃짱");
    }

    @Test
    @DisplayName("우승자가 이동한 칸수가 경기에 참여한 차량들이 이동한 칸수 중 최대 칸수와 일치하는지 검사한다.")
    void maxPositionTest() {
        Car gitJjang = new Car(new Name("깃짱"));
        Car irene = new Car(new Name("이리내"));
        Cars cars = new Cars(Arrays.asList(gitJjang, irene));

        gitJjang.move(true);
        irene.move(true);
        gitJjang.move(true);
        irene.move(false);
        gitJjang.move(true);
        irene.move(false);

        assertThat(cars.getWinners()).contains("깃짱");
        assertThat(gitJjang.getPosition()).isEqualTo(3);
    }

    @RepeatedTest(100)
    @DisplayName("자동차가 한번 이동한 칸수가 0칸 또는 1칸인지 검사한다")
    void carMoveTest() {
        Car gitJjang = new Car(new Name("깃짱"));
        Car secondGitJjang = new Car(new Name("깃짱부캐"));
        Car irene = new Car(new Name("이리내"));
        Car secondIrene = new Car(new Name("이리내부캐"));
        Cars cars = new Cars(Arrays.asList(gitJjang, secondGitJjang, irene, secondIrene));

        cars.moveAllCarsOnce(new ThresholdCarMoveManager());
        List<Car> carsAfterMove = cars.getCurrentResult();

        List<Car> result = carsAfterMove.stream()
                .dropWhile(car -> car.getPosition() > 1)
                .collect(toList());
        assertThat(result).containsExactlyInAnyOrderElementsOf(carsAfterMove);
    }

}
