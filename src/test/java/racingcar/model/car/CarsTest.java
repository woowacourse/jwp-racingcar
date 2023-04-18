package racingcar.model.car;

import racingcar.model.manager.ThresholdCarMoveManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CarsTest {

    @Nested
    @DisplayName("비정상 입력값이 들어온 케이스")
    class invalidInputTest {

        @Test
        @DisplayName("경주에 참여하는 자동차가 1대 이하면 예외처리 한다.")
        void carNumberTest() {
            List<String> names = new ArrayList<>(List.of("깃짱"));
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Cars.from(names))
                    .withMessage("2개 이상의 자동차를 입력해 주세요.");
        }

        @Test
        @DisplayName("6글자 이상의 이름은 예외 처리한다.")
        void carNameLengthTest() {
            List<String> names = new ArrayList<>(List.of("깃짱", "이리내이리내"));
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Cars.from(names))
                    .withMessage("5자 이하의 이름을 입력해주세요.");
        }

    }

    @Nested
    @DisplayName("정상 입력값이 들어온 케이스")
    class validInputTest {

        @Test
        @DisplayName("자동차 이름들이 올바른 경우 잘 추가되는지 검사한다.")
        void 정상_입력() {
            Car gitJjang = Car.from("깃짱");
            Car irene = Car.from("이리내");
            List<Car> cars = new ArrayList<>(List.of(gitJjang, irene));

            assertThatCode(()-> new Cars(cars)).doesNotThrowAnyException();
        }

    }

    @RepeatedTest(100)
    @DisplayName("자동차가 한번 이동한 칸수가 0칸 또는 1칸인지 검사한다")
    void carMoveTest() {
        Car gitJjang = Car.from("깃짱");
        Car secondGitJjang = Car.from("깃짱부캐");
        Car irene = Car.from("이리내");
        Car secondIrene = Car.from("이리내부캐");
        Cars cars = new Cars(Arrays.asList(gitJjang, secondGitJjang, irene, secondIrene));

        cars.moveCars(new ThresholdCarMoveManager());
        List<Car> carsAfterMove = cars.getCars();
        assertThat(carsAfterMove.stream().dropWhile(car -> car.getPosition() > 1).collect(Collectors.toList()).equals(carsAfterMove));
    }

}
